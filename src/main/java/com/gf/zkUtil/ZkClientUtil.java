package com.gf.zkUtil;

import java.util.ArrayList;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZkClientUtil {
	@Autowired
	ZkConfigBean zkConfig;
	
	public ZkClient createClient(){
		ZkClient client = new ZkClient(zkConfig.getUrl(), 
				zkConfig.getSessionTimeout(), zkConfig.getConnectionTimeout());
		return client;
	}
	
	public void closeClient(ZkClient client){
		client.close();
	}
	
	public List<String> getChildNode(ZkClient client,String path){
		List<String> children = client.getChildren(path);
		return children;
	}
	
	public List<String> getAllChildData(ZkClient client,String path){
		List<String> list = new ArrayList();
		List<String> childNode = getChildNode(client, path);
		childNode.forEach(childPath->{
			list.add(client.readData(path+"/"+childPath));
		});
		return list;
	}
	
	public void createEphemeralSequentialNode(ZkClient client,String path,boolean ifNullCreateParent,Object data){
		int index = -1;
		if(ifNullCreateParent && (index = path.indexOf("/",1)) > 0){
			String parentPath = path.substring(0,index);
			client.createPersistent(parentPath, true);
		}
		client.createEphemeralSequential(path, data);
	}
	
	public void addChildNodeChangeListener(ZkClient client,String path,IZkChildListener listener){
		client.subscribeChildChanges(path, listener);
	}
}
