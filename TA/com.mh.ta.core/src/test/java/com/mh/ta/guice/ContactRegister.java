package com.mh.ta.guice;

import com.google.inject.Inject;
import com.mh.ta.core.config.FrameworkSettings;

public class ContactRegister {
	@Inject
	private Contact contact;

	@Inject
	private FrameworkSettings settings;

	public FrameworkSettings getSettings() {
		return this.settings;
	}

	public Contact getContact() {
		return this.contact;
	}
}
