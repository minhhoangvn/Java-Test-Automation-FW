package com.mh.ta.person;

public abstract class ServicesFactory implements IServices {
	@Override
	public void addPerson() {
		persons.set(createPerson());
		System.err.println("Person info after create: " + persons.get().toString());
	}

	@Override
	public void deletePerson() {
		persons.remove();
	}

	@Override
	public void editPerson() {
		System.err.println("Person info before edit main method: " + this.persons.get().toString());
		modifyPerson();
		System.err.println("Person info after edit: " + persons.get().toString());
	}

	@Override
	public Person getPerson() {
		return persons.get();
	}

	protected ThreadLocal<Person> persons = new ThreadLocal<Person>();
}
