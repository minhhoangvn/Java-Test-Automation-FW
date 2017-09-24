
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

import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.mh.ta.core.config.LoggerFactory;
import com.mh.ta.core.config.annotation.ApplicationConfig;
import com.mh.ta.core.config.settings.DataSourceConfig;
import com.mh.ta.core.config.settings.MainConfig;
import com.mh.ta.core.exception.TestContextException;

/**
 * @author minhhoang
 *
 */
public class SqlQuery {
	private Logger log = LoggerFactory.instance().createClassLogger(getClass());
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
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = this.createConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			this.closeConnection(conn);
		} catch (SQLException e) {
			log.error(e.getMessage(), e.getCause());
			throw new TestContextException("Has error in execute query " + e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				log.error("Has error in close conection ", e);
			}
		}
	}

	public void executeStoredProcedure(String query) {
		Connection conn = this.createConnection();
		this.call(conn, query);
		this.closeConnection(conn);
	}

	public List<List<String>> getListQueryResult(String query) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.createConnection();
			List<List<String>> listResult = new ArrayList<>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			this.createListResultColumnName(rs.getMetaData());
			while (rs.next())
				listResult.add(this.addRowResult(rs));
			return listResult;
		} catch (SQLException e) {
			log.error(e.getMessage(), e.getCause());
			throw new TestContextException("Cannot execute query, " + e);
		} finally {
			this.closeResutSet(rs);
			this.closeStatement(stmt);
			this.closeConnection(conn);
		}
	}

	private Connection createConnection() {
		try {
			DataSourceConfig configDataBase = this.config.getDataSourceConfig();
			Class.forName(configDataBase.getDatabaseDriver());
			return DriverManager.getConnection(configDataBase.getDatabaseConnectionString(),
					configDataBase.getDatabaseUserName(), configDataBase.getDatabasePassword());
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e.getMessage(), e.getCause());
			throw new TestContextException("Cannot create database connection, " + e);
		}
	}

	private void call(Connection conn, String query) {
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall(query);
			cs.executeQuery();
		} catch (SQLException e) {
			log.error(e.getMessage(), e.getCause());
			throw new TestContextException("Error in execute store procedure, " + e);
		} finally {
			try {
				if (cs != null)
					cs.close();
			} catch (SQLException e) {
				log.error(e.getMessage(), e.getCause());
			}
		}
	}

	private void createListResultColumnName(ResultSetMetaData rsmd) throws SQLException {
		this.listColumnName = new ArrayList<>();
		int totalColumn = rsmd.getColumnCount();
		for (int count = 1; count <= totalColumn; count++) {
			this.listColumnName.add(rsmd.getColumnLabel(count));
		}
	}

	private List<String> addRowResult(ResultSet rs) throws SQLException {
		List<String> rowResult = new ArrayList<>();
		for (int count = 0; count < this.listColumnName.size(); count++)
			rowResult.add(rs.getString(this.listColumnName.get(count)));
		return rowResult;
	}

	private void closeConnection(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e1) {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e.getCause());
				}
			}
	}

	private void closeStatement(Statement stmt) {
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e1) {
				try {
					stmt.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e.getCause());
				}
			}
	}

	private void closeResutSet(ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e1) {
				try {
					rs.close();
				} catch (SQLException e) {
					log.error(e.getMessage(), e.getCause());
				}
			}
	}
}
