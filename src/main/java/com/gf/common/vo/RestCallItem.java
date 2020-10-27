package com.gf.common.vo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gf.zkUtil.ZkClientUtil;
import com.gf.zkUtil.bean.ZkPath;

/**
 * <p>Description: 可用接口服务 
 * 初始化远程接口,可调用节点(在zk中注册了则表示改接口服务可用),监听节点的变化
 * </p> 
 * @author ganF
 * @date 2020-10-27
 */
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
