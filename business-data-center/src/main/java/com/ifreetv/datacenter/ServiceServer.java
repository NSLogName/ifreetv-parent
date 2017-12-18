/**
 * ServiceServer.java
 * @author huangwei
 * @since 2016-8-11
 *  描述：
 */
package com.ifreetv.datacenter;

import com.alibaba.druid.support.monitor.MonitorClient;
import com.ifreetv.baseutils.utils.LoggerUtils;
import com.ifreetv.baseutils.utils.NotQuitThread;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * ServiceServer.java
 * @author huangwei
 * @since 2016-8-11
 *  描述：
 */
public class ServiceServer {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		context.start();
		Thread thread = new NotQuitThread();
		thread.start();
	}
}
