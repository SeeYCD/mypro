package com.dosome;

import myproject.frame.dao.DaoA;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
/**
 * 初始化加载资源测试
 * @author user
 *
 */
@Service
public class SourceInit2 implements InitializingBean{
	@Autowired
	private DaoA daoa;
	@Override
	public void afterPropertiesSet() throws Exception {
		StringUtils.equals("","");
		for(int i=0;i<20;i++){
			System.out.println("dmeo==================");
 		}
	};

}
