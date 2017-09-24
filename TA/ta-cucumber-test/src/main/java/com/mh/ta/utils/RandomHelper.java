
package com.mh.ta.utils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHelper {
	private RandomHelper(){
		throw new IllegalStateException("RandomHelper Utility class");
	}
	public static <T> T getRandomItemInList(List<T> listItem) {
		int size = listItem.size();
		int itemIndex = ThreadLocalRandom.current().nextInt(0, size);
		return listItem.get(itemIndex);
	}
}
