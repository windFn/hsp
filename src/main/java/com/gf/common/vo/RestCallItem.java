package com.gf.common.vo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.ZKUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gf.zkUtil.ZkClientUtil;
import com.gf.zkUtil.ZkPath;

@Component
public class RestCallItem {
	@Autowired
	private ZkClientUtil clientUtil;
	@Autowired
	private ZkPath path;
	
	private ZkClient client;
	
	private List<String> productServices;
	
	public List<String> getProductServices() {
		return productServices;
	}

	public void setProductServices(List<String> productServices) {
		this.productServices = productServices;
	}
	
	@PostConstruct
	public void init(){
		client = clientUtil.createClient();
    	//获取节点中所有注册的服务地址
    	List<String> childData = clientUtil.getAllChildData(client, path.getProductPath());
    	//初始化调用服务地址列表
    	this.setProductServices(childData);
    	//监听服务节点变化
    	clientUtil.addChildNodeChangeListener(client, path.getProductPath(), 
    			(parentPath,currentChilds)->{
    				this.setProductServices(clientUtil.getAllChildData(client, path.getProductPath()));
    			}
    		);
	}
	
	@PreDestroy
	public void destory(){
		clientUtil.closeClient(client);
	}

}
