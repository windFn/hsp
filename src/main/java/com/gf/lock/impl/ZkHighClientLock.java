package com.gf.lock.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;
import com.gf.lock.IDistributedLock;
import com.gf.zkUtil.ZkClientUtil;

/**
 * <p>Description: zkclient临时顺序节点实现分布式锁(高效)
 * 	  处于第一个节点的client获取到锁,若当前不是第一个节点只需监控上一个节点的状态即可，
 *   如果上一个节点已删除则当前为第一个节点可获取到锁
 * </p> 
 * @author ganF
 * @date 2020-10-27
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ZkHighClientLock implements IDistributedLock{
	@Autowired
	private ZkClientUtil clientUtil;
	private ZkClient client;
	private final String childPath = "/child";
	private String currentPath;
	private String beforePath;
	@Override
	public boolean tryLock(String lockPath) {
		if(client == null){
			client = clientUtil.createClient();
		}
		try {
			if(StringUtils.isEmpty(currentPath)){
				currentPath = clientUtil.createEphemeralSequentialNode(client, lockPath+childPath, true, null);
			}
			List<String> childNodes = clientUtil.getChildNode(client, lockPath);
			Collections.sort(childNodes);
			if(currentPath.equals(lockPath+"/"+childNodes.get(0))){//当前为第一个顺序节点可获取锁
				return true;
			}else{
				beforePath =lockPath+"/"+childNodes.get(childNodes.indexOf(currentPath.replace(lockPath+"/", "")) -1);
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public void unLock() {
		if(client != null){
			clientUtil.removeNode(client, currentPath);
			clientUtil.closeClient(client);
		}
	}
	
	@Override
	public void waitLock(String lockPath, TimeUnit unit, Long timeOut) {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		IZkChildListener listener = (parentPath,currentChilds)->{
			if(currentChilds == null){// 当前节点已删除
				countDownLatch.countDown();
			}
		};
		clientUtil.addChildNodeChangeListener(client, beforePath, listener);
		try {
			if(unit == null){
				countDownLatch.await();
			}else{
				countDownLatch.await(timeOut,unit);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clientUtil.removeChildNodeChangeListener(client, beforePath, listener);
	}
	
}
