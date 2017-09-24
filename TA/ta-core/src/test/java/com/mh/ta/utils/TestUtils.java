
package com.mh.ta.utils;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author minhhoang
 *
 */
public class TestUtils {
	@SuppressWarnings("unused")
	public static void startRunStubTest(Runnable... runnables) throws InterruptedException, ExecutionException {
		Runnable exec = () -> {
			int size = runnables.length;
			ExecutorService executor = Executors.newFixedThreadPool(100);
			ArrayList<Future<?>> futures = new ArrayList<>();
			for (int count = 0; count < size; count++) {
				Future<?> f = executor.submit(runnables[count]);
				futures.add(f);
			}
			for (Future<?> future : futures) {
				try {
					future.get();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			boolean allDone = true;
			for (Future<?> future : futures) {
				allDone &= future.isDone();
			}
		};
		Thread t = new Thread(exec);
		t.start();
		t.join();
	}
}
