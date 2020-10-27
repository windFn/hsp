package com.gf.lock;

import java.util.concurrent.TimeUnit;


/**
 * <p>Description: 分布式锁接口定义</p> 
 * @author ganF
 * @date 2020-10-27
 */
public interface IDistributedLock {
	default void lock(String lockPath){
		if(!tryLock(lockPath)){
			waitLock(lockPath,null,null);
			lock(lockPath);
		}
	}
	
	default boolean tryLock(String lockPath, TimeUnit unit, Long timeOut){
		long currentTime = System.currentTimeMillis();
		while(true){
			if(!tryLock(lockPath)){//未获取到锁
				if(timeOut < 0){//如果超时直接返回false
					return false;
				}
				waitLock(lockPath,unit,timeOut);//等待锁
				long cost = System.currentTimeMillis() - currentTime;
				timeOut = unit.convert(unit.toMillis(timeOut) - cost,TimeUnit.MILLISECONDS);
			}else{
				return true;
			}
		}
	}
	
	public boolean tryLock(String lockPath);
	
	public void unLock();
	
	public void waitLock(String lockPath,TimeUnit unit, Long timeOut);
}
