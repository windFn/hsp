package com.gf.common.util;

import java.util.List;
import java.util.Random;

/**
 * <p>Description: 远程接口调用 随机选取服务节点</p> 
 * @author ganF
 * @date 2020-10-27
 */
public class RandomChoseUtil {
	
	public static <T> T choseRandom(List<T> list){
		if(list != null && !list.isEmpty()){
			int size = list.size();
			Random random = new Random();
			int chose = random.nextInt(size);
			return list.get(chose);
		}
		return null;
	}
	
	
}
