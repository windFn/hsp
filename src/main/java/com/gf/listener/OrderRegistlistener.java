package com.gf.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gf.common.vo.RestCallItem;
import com.gf.zkUtil.ZkClientUtil;
import com.gf.zkUtil.ZkPath;

public class OrderRegistlistener implements ServletContextListener {
	private ZkClientUtil clientUtil = null;
	private ZkClient client = null;
	private final static Logger log = LoggerFactory.getLogger(OrderRegistlistener.class);
    public void contextDestroyed(ServletContextEvent sce)  {
    	clientUtil.closeClient(client);
    }
    
    public void contextInitialized(ServletContextEvent sce)  {
    	try {
    		ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        	//rest调用服务地址列表
        	RestCallItem item = app.getBean(RestCallItem.class);
        	//rest接口注册的zk路径
        	ZkPath path = app.getBean(ZkPath.class);
        	clientUtil = app.getBean(ZkClientUtil.class);
        	client = clientUtil.createClient();
        	//获取节点中所有注册的服务地址
        	List<String> childData = clientUtil.getAllChildData(client, path.getProductPath());
        	//初始化调用服务地址列表
        	item.setProductServices(childData);
        	//监听服务节点变化
        	clientUtil.addChildNodeChangeListener(client, path.getProductPath(), 
        			(parentPath,currentChilds)->{
        				item.setProductServices(currentChilds);
        			}
        		);
		} catch (Exception e) {
			log.error("初始化rest服务调用地址失败",e);
		}
    }
	
}
