package com.mh.ta.person;

import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import com.google.inject.Inject;
import com.google.inject.name.Named;

@Guice(modules = { PersonModule.class })
public class TestProvider {

	@Inject
	@Named("ITServices")
	ServicesFactory it;

	@Inject
	@Named("OfficeServices")
	ServicesFactory of;

	@Test
	public void testOffice() {
		System.err.println("=========== TEST OFFICE ===============");
		it.addPerson();
		of.addPerson();
		of.editPerson();
		it.editPerson();
		it.deletePerson();
		of.deletePerson();
		System.err.println(it.getPerson());
		System.err.println(of.getPerson());
	}

	@Test
	public void testIT() {
		System.err.println("=========== TEST IT ===============");
		it.addPerson();
		of.addPerson();
		of.editPerson();
		it.editPerson();
		it.deletePerson();
		of.deletePerson();
		System.err.println(it.getPerson());
		System.err.println(of.getPerson());
	}
}
