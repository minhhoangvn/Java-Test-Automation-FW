package com.mh.ta.core.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.mh.ta.core.annotation.ApplicationConfig;
import com.mh.ta.core.config.DataSourceConfig;
import com.mh.ta.core.config.MainConfig;
import com.mh.ta.core.exception.TestContextException;

public class SqlQuery {

	private List<String> listColumnName;

	MainConfig config;

	DataSourceConfig dataSourceConfig;

	@Inject
	public SqlQuery(@ApplicationConfig MainConfig config) {
		this.config = config;
	}

	public SqlQuery(DataSourceConfig config) {
		this.dataSourceConfig = config;
	}

	public void executeQuery(String query) {
		Connection conn = this.createConnection();
		this.execute(conn, query);
		this.closeConnection(conn);
	}

	public void executeStoredProcedure(String query) {
		Connection conn = this.createConnection();
		this.call(conn, query);
		this.closeConnection(conn);
	}

	public List<List<String>> getListQueryResult(String query) {
		Connection conn = null;
		try {
			conn = this.createConnection();
			List<List<String>> listResult = new ArrayList<>();
			ResultSet rs = this.execute(conn, query);
			if (rs != null)
				this.createListResultColumnName(rs.getMetaData());
			while (rs.next())
				listResult.add(this.addRowResult(rs));
			return listResult;
		} catch (SQLException e) {
			throw new TestContextException("Cannot execute query, " + e);
		} finally {
			this.closeConnection(conn);
		}
	}

	private Connection createConnection() {
		try {
			DataSourceConfig dataSourceConfig = this.config.getDataSourceConfig();
			Class.forName(dataSourceConfig.getDatabaseDriver());
			return DriverManager.getConnection(dataSourceConfig.getDatabaseConnectionString(),
					dataSourceConfig.getDatabaseUserName(), dataSourceConfig.getDatabasePassword());
		} catch (ClassNotFoundException | SQLException e) {
			throw new TestContextException("Cannot create database connection, " + e);
		}
	}

	private ResultSet execute(Connection conn, String query) {
		try {

			Statement stmt = conn.createStatement();
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			throw new TestContextException("Has error in execute query " + e);
		}
	}

	private void call(Connection conn, String query) {
		try {
			CallableStatement cs = conn.prepareCall(query);
			cs.executeQuery();
		} catch (SQLException e) {
			throw new TestContextException("Error in execute store procedure, " + e);
		}
	}

	private void createListResultColumnName(ResultSetMetaData rsmd) throws SQLException {
		this.listColumnName = new ArrayList<String>();
		int totalColumn = rsmd.getColumnCount();
		for (int count = 1; count <= totalColumn; count++) {
			this.listColumnName.add(rsmd.getColumnLabel(count));
		}
	}

	private List<String> addRowResult(ResultSet rs) throws SQLException {
		List<String> rowResult = new ArrayList<String>();
		for (int count = 0; count < this.listColumnName.size(); count++)
			rowResult.add(rs.getString(this.listColumnName.get(count)));
		return rowResult;
	}

	private void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
				conn = null;
			} catch (SQLException e1) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					// Ignore after try to close DB connection
				}
			}
	}
}