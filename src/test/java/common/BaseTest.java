package common;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application_test.xml")
public class BaseTest {
	@Autowired
	protected ApplicationContext ctx;
	@Before
	public void logback(){
		LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
	    JoranConfigurator configurator = new JoranConfigurator();
	    configurator.setContext(lc);
	    lc.reset();
	    try {
	    	InputStream inputStream = Resources.getResourceAsStream("logback.xml");
	        configurator.doConfigure(inputStream);//加载logback配置文件
	   } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
