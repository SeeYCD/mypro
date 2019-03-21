package myproject.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import sun.misc.Unsafe;

public class UnsafeTest {

	private static Unsafe unsafe;  
	  
    static {  
        try {  
            //通过反射获取rt.jar下的Unsafe类  
            Field field = Unsafe.class.getDeclaredField("theUnsafe");  
            field.setAccessible(true);  
            unsafe = (Unsafe) field.get(null);  
        } catch (Exception e) {  
            System.out.println("Get Unsafe instance occur error"+ e);  
        }  
    }  
  
  
  
  
    public static void main(String[] args) throws Exception  
    {  
        Class clazz = Target.class;  
        Field[] fields = clazz.getDeclaredFields();  
        System.out.println("fieldName:fieldOffset");  
        for (Field f : fields) {  
            // 获取属性偏移量，可以通过这个偏移量给属性设置  
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));  
        }  
        Target target = new Target();  
        Field intFiled  =  clazz.getDeclaredField("intParam")  ;  
        int a=(Integer)intFiled.get(target ) ;  
        System.out.println("原始值是:"+a);  
//        //intParam的内存地址偏移是12 原始值是3 我们要改为10，
//        每个属性的内存偏移是不一致的，通过对象+属性的内存偏移获取对于属性主内存的值，和当前线程的3比较，
//        相同则将10替换保存主内存的值  ，刷新主内存，其他线程值失效
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10));  
        int b=(Integer)intFiled.get(target) ;  
        System.out.println("改变之后的值是:"+b);  
//        //这个时候已经改为10了,所以会返回false  
        System.out.println(unsafe.compareAndSwapInt(target, 12, 3, 10)); 
        List<String> list=new ArrayList<>();
        //自行设置偏移量
        unsafe.putObjectVolatile(list, 1111, "2222");
        //判断对象的偏移量地址是否保存数据
        System.out.println("dizhi222:"+unsafe.getObjectVolatile(list, 1111));
        System.out.println(unsafe.compareAndSwapObject(list, 1111, "2222", "221"));
        System.out.println(unsafe.getObjectVolatile(list, 1111));
        System.out.println(unsafe.compareAndSwapObject(target, 24, null, "5"));  
    }  
}  
  
  
 class Target {  
     long longParam;
     long longParam2;
     String strParam;  
     String strParam2;  
     Target tt;
     Target tt2;
     int intParam=3; 
     int intParam2=3;  

}  
