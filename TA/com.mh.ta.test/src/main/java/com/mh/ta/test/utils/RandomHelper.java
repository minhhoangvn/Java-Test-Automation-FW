package com.mh.ta.test.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {
	public static <T> T getRandomItemInList(List<T> listItem) {
		int size = listItem.size();
		int itemIndex = ThreadLocalRandom.current().nextInt(0, size + 1);
		return listItem.get(itemIndex);
	}
}
