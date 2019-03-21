package myproject.frame.init;

import myproject.frame.dao.DaoA;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 初始化加载资源
 * @author user
 *
 */
@Service
public class SourceInit implements InitializingBean{
	@Autowired
	private DaoA daoa;
	@Override
	public void afterPropertiesSet() throws Exception {
		for(int i=0;i<20;i++){
			System.out.println("==================");
 		}
	};

}
