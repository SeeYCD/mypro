package myproject.tools;

import java.io.File;

/**
 * 删除文件夹
 * @author user
 *
 */
public class DeleteFile {
	/**
	 * 递归删除文件夹
	 * @param file
	 * @return
	 */
	public static int deleteFolder(File file){
 		if(!file.exists()){//文件是否存在
 			System.out.println("notexsist:"+file.getPath());
			return -1;
		}
		if(file.isFile()){//是文件删除
			file.delete();
			System.out.println("deletefile:"+file.getName());
			return 0;
		}
  		for(File f:file.listFiles()){//循环文件夹删除文件
 			deleteFolder(f);
		}
		file.delete();//删除文件夹
		System.out.println("deletefolder:"+file.getName());
  		return 0;
 	}
	
//	@Test
	public static  void main(String arg[]){
		String path="C:/Users/user/Desktop/ceshi";
		File file=new File(path);
		System.out.println(DeleteFile.deleteFolder(file));
	}
}
