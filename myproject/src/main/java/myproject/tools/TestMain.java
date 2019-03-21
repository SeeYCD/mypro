package myproject.tools;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import myproject.frame.entity.Response;
import myproject.frame.entity.User;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;

public class TestMain {
//	@Test
	public void testReadFile(){
		try {
			String path="D:/CRH/workspace/config/config.properties";
			String classPath="testFile/config.properties";
//			PropertitiesHandle.readPropertyChar(classPath,"name");
//			PropertitiesHandle.readPropertyByte(classPath,"dept");
// 			PropertitiesHandle.readFile(path,"class");
// 			PropertitiesHandle.setProperties(path, "class", "七年级");
 			PropertitiesHandle.writeFile(classPath, "dept", "国安");
 			PropertitiesHandle.writeFile(classPath, "test", "太极");

		} catch (IOException e) {
 			e.printStackTrace();
		}
	}
	
//	@Test
	public void testSearch(){
		int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		int key=18;
//		System.out.println("二分法循环："+BinarySearch.binarySearch(arr, key));
		System.out.println("递归查询："+BinarySearch.recursionBinarySearch(arr, key, 0, arr.length-1));
	}
//	@Test
	public void testBubbleSort(){
		int [] arr={0,1,2,1,4,9,10,8,7,6,13,11};
 		System.out.println("sort前:"+Arrays.toString(arr));
		BubbleSort.bubbleSortAsc(arr);
 		System.out.println("sort后:"+Arrays.toString(arr));
 	}
//	@Test
	public void testDeleteFolder(){
		String path="C:/Users/user/Desktop/ceshi";
		File file=new File(path);
		System.out.println(DeleteFile.deleteFolder(file));
	}
//	@Test
	public void cloneT(){
		User u=new User();
		u.setId(11);
		Response res=new Response();
		res.setUser(u);
		Response res2=(Response) DeepClone.deepClone(res);
		System.out.println(res2.getUser().getId());
	}
//	@Test
	public void classLoaderT(){
		 try {
//			 Set result=ClassLoadT.doFindAllClassPathResources("myproject.tools");
//			 System.out.println(result==null?"":result.size());

			 Resource result[]=ClassLoadT.classLoader("classpath*:myproject/tools/**/*.class");
			 System.out.println(result==null?"":result.length);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void beanUtilsT(){
		User u=BeanUtils.instantiate(User.class);
		System.out.println(u.toString());
	}
	
}
