package com.gf.lock.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import common.BaseTest;

public class ZkClientLockTest extends BaseTest{
	int i = 0;
	
	@Test
	public void testLock() throws Exception {
		long start = System.currentTimeMillis();
		int num = 60;
		CountDownLatch latch = new CountDownLatch(num);
		String lockPath = "/lock";
		for(int j=0; j<num;j++){
			new Thread(()->{
				ZkClientLock zkLock = ctx.getBean(ZkClientLock.class);
				zkLock.lock(lockPath);
				i++;
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				zkLock.unLock();
				latch.countDown();
			}).start();
		}
		latch.await();
		System.out.println("************"+i+"******************");
		System.out.println(System.currentTimeMillis() - start);
	}
	
	@Test
	public void testTryLock(){
		String lockPath = "/lock";
		ZkClientLock zkLock = ctx.getBean(ZkClientLock.class);
		System.out.println(1111111111);
		boolean tryLock = zkLock.tryLock(lockPath,TimeUnit.SECONDS,10L);
		System.out.println(tryLock);
		System.out.println(222222222);
		zkLock.unLock();
	}
}
