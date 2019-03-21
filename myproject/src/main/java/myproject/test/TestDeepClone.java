package myproject.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import myproject.frame.entity.User;

import org.junit.Test;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class TestDeepClone extends TestClass{
	public static void main(String[] args) {
		try {
			FileInputStream fileI=new FileInputStream(new File(""));
			ObjectInputStream objI=new ObjectInputStream(fileI);
			objI.readObject();
			fileI.close();
			objI.close();
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		} catch (IOException e) {
  			e.printStackTrace();
		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
		}
	}
//	@Test
	public void tex(){
  		String i=EnumDemo.MYSQL.getValue();
  		System.out.println(i);
 	}
	@Test
	public void cloneDeep(){
		User user=new User();
		User userc=new User();
		User userdc=new User();

		user.setId(11);
		user.setPartName("edc");
		ByteArrayOutputStream  bout=new ByteArrayOutputStream ();
		ObjectOutputStream oout;
		ByteArrayInputStream bint;
		ObjectInputStream oint;
 		try {
 			oout = new ObjectOutputStream(bout);
 			oout.writeObject(user);
 			bint=new ByteArrayInputStream(bout.toByteArray());
 			oint=new ObjectInputStream(bint);
 			userdc=(User) oint.readObject();
 			userc=user.clone(); 
 			System.out.println(user.getPartName()==userdc.getPartName());
 			System.out.println(user.getPartName()==userc.getPartName());
  		} catch (IOException|ClassNotFoundException|CloneNotSupportedException e) {
 			e.printStackTrace();
		}
		 
 	}
}
