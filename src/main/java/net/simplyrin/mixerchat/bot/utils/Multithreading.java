/*
 *     Copyright (C) 2018  Hyperium <https://hyperium.cc/>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.simplyrin.mixerchat.bot.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Mitchell Katz on 5/8/2017.
 */
public class Multithreading {

	private static AtomicInteger counter = new AtomicInteger(0);

	public static ExecutorService POOL = Executors.newFixedThreadPool(8,r -> new Thread(r, String.format("Thread %s", counter.incrementAndGet())));
	private static ScheduledExecutorService RUNNABLE_POOL = Executors.newScheduledThreadPool(2, r -> new Thread(r, "Thread " + counter.incrementAndGet()));

	public static void runAsync(Runnable runnable) {
		POOL.execute(runnable);
	}

	public static void schedule(Runnable r, long initialDelay, long delay, TimeUnit unit) {
		RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
	}

	public static int getTotal() {
		return ((ThreadPoolExecutor) Multithreading.POOL).getActiveCount();
	}

}
