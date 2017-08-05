package com.mh.ta.test;

import java.util.ArrayList;
import java.util.List;

public class WeatherServices implements Observable {
	List<Observers> listObservers = new ArrayList<Observers>();
	int temp = 0;

	public void updateWeatherTemp(int value) {
		this.temp = value;
		this.notifyUpdate();
	}

	@Override
	public void notifyUpdate() {
		for (Observers o : listObservers)
			o.update(this.temp);
	}

	@Override
	public void attachObject(Observers obj) {
		listObservers.add(obj);

	}

	@Override
	public void detachObject(Observers obj) {
		listObservers.remove(obj);
	}

}
