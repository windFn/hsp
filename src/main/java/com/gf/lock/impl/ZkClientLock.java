package com.gf.lock.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gf.lock.IDistributedLock;
import com.gf.zkUtil.ZkClientUtil;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ZkClientLock implements IDistributedLock{
	@Autowired
	private ZkClientUtil clientUtil;
	private ZkClient client;
	private final String childPath = "/child";
	@Override
	public boolean tryLock(String lockPath) {
		if(client == null){
			client = clientUtil.createClient();
		}
		try {
			clientUtil.createEphemeralNode(client, lockPath+childPath, true, null);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void unLock() {
		if(client != null)
			client.close();
	}
	
	@Override
	public void waitLock(String lockPath, TimeUnit unit, Long timeOut) {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		IZkChildListener listener = (parentPath,currentChilds)->{
			if(currentChilds == null){
				countDownLatch.countDown();
			}
		};
		clientUtil.addChildNodeChangeListener(client, lockPath+childPath, listener);
		if(!clientUtil.existNode(client, lockPath+childPath)){
		}else{
			try {
				if(unit == null){
					countDownLatch.await();
				}else{
					countDownLatch.await(timeOut,unit);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		clientUtil.removeChildNodeChangeListener(client, lockPath+childPath, listener);
	}
	
}
