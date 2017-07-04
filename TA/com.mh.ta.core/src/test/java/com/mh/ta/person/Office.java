package com.mh.ta.person;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Office extends ServicesFactory {
	@Override
	public Person createPerson() {
		Person person = new Person((Thread.currentThread().getId()) + "_OFFICE_" + UUID.randomUUID().toString(),
				(Thread.currentThread().getId()) + "_OFFICE_" + UUID.randomUUID().toString(),
				ThreadLocalRandom.current().nextInt(1, 101), new Random().nextBoolean());
		return person;
	}

	@Override
	public void modifyPerson() {
		System.err.println("Person before edit in implement: "+ this.persons.get().toString());
		persons.get().setAddress((Thread.currentThread().getId()) + "_OFFICE_" + UUID.randomUUID().toString());
		persons.get().setName((Thread.currentThread().getId()) + "_OFFICE_" + UUID.randomUUID().toString());
		persons.get().setAge(ThreadLocalRandom.current().nextInt(1, 101));
		persons.get().setMarried(new Random().nextBoolean());

	}
}
