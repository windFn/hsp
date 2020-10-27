package com.gf.zkUtil;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gf.zkUtil.bean.ZkConfigBean;

@Component
public class ZkClientUtil {
	@Autowired
	ZkConfigBean zkConfig;
	
	/**
	 * 创建zkclient
	 * @return
	 */
	public ZkClient createClient(){
		ZkClient client = new ZkClient(zkConfig.getUrl(), 
				zkConfig.getSessionTimeout(), zkConfig.getConnectionTimeout());
		return client;
	}
	
	/**
	 * 关闭client连接
	 * @param client
	 */
	public void closeClient(ZkClient client){
		client.close();
	}
	
	/**
	 * 获取path路径下的直接子节点
	 * @param client
	 * @param path
	 * @return
	 */
	public List<String> getChildNode(ZkClient client,String path){
		List<String> children = client.getChildren(path);
		return children;
	}
	
	/**
	 * 获取path路径下的直接子节点中存储的数据
	 * @param client
	 * @param path
	 * @return
	 */
	public List<String> getAllChildData(ZkClient client,String path){
		List<String> list = new ArrayList<String>();
		List<String> childNode = getChildNode(client, path);
		childNode.forEach(childPath->{
			list.add(client.readData(path+"/"+childPath));
		});
		return list;
	}
	
	/**
	 * 创建持久节点
	 * @param client
	 * @param path
	 * @param ifNullCreateParent
	 * @param data
	 */
	public void createPersistentNode(ZkClient client,String path,boolean ifNullCreateParent,Object data){
		client.createPersistent(path, ifNullCreateParent);
		client.writeData(path, data);
	}
	
	/**
	 * 创建临时节点
	 * @param client
	 * @param path
	 * @param ifNullCreateParent
	 * @param data
	 */
	public void createEphemeralNode(ZkClient client,String path,boolean ifNullCreateParent,Object data){
		int index = -1;
		if(ifNullCreateParent && (index = path.indexOf("/",1)) > 0){
			String parentPath = path.substring(0,index);
			client.createPersistent(parentPath, true);
		}
		client.createEphemeral(path, data);
	}
	
	/**
	 * 创建临时顺序节点
	 * @param client
	 * @param path
	 * @param ifNullCreateParent 是否需要创建父节点
	 * @param data
	 */
	public void createEphemeralSequentialNode(ZkClient client,String path,boolean ifNullCreateParent,Object data){
		int index = -1;
		if(ifNullCreateParent && (index = path.indexOf("/",1)) > 0){
			String parentPath = path.substring(0,index);
			client.createPersistent(parentPath, true);
		}
		client.createEphemeralSequential(path, data);
	}
	
	/**
	 * 添加子节点变化的监听事件
	 * @param client
	 * @param path
	 * @param listener
	 */
	public void addChildNodeChangeListener(ZkClient client,String path,IZkChildListener listener){
		client.subscribeChildChanges(path, listener);
	}
	
	/**
	 * 移除子节点变化的监听事件
	 * @param client
	 * @param path
	 * @param listener
	 */
	public void removeChildNodeChangeListener(ZkClient client,String path,IZkChildListener listener){
		client.unsubscribeChildChanges(path, listener);
	}
	
	/**
	 * 是否存在path节点
	 * @param client
	 * @param path
	 * @return
	 */
	public boolean existNode(ZkClient client,String path){
		return client.exists(path);
	}
}
