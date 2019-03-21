package myproject.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 深度克隆
 * @author user
 *
 */
public class DeepClone {
	/**
	 * 要求obj及其属性对象，实序列化 serializable
	 * @param obj
	 * @return
	 */
	public static Object deepClone(Object obj) {
		Object obj2=null;
		if(obj==null)
			return obj2;
		ByteArrayOutputStream bay=new ByteArrayOutputStream();
		ObjectOutputStream oos=null;
		ObjectInputStream ois=null;
		try{
			oos=new ObjectOutputStream(bay);
			oos.writeObject(obj);
			ois=new ObjectInputStream(new ByteArrayInputStream(bay.toByteArray()));
			obj2=ois.readObject();
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return obj;
		
	}
}
