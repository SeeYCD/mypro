package myproject.test.annotation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils; 
 
/**
 * 自定义CustomOne注解使用的demo 获取路径下的所有class,解析出使用CustomOne注解的class和method
 * 将映射关系保存在customOneClassMap
 * 
 * @author user
 *
 */
public class CustomOneTest {
	// name和class以及method的映射关系
	public static Map<String, Object> customOneClassMap = new HashMap<>();
	//目标包路径下的所有class
	public static Set<Class<?>> classes = new LinkedHashSet<>();

 	public static void main(String[] args) {
		String packageName = "gitdemo.demo.annotationtest.userdemo";
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if (url != null) {
					String protocol = url.getProtocol();
					if (protocol.equals("file")) {
						// 转码
						String packagePath = URLDecoder.decode(url.getFile(), "UTF-8");
						// 添加
						addClass(packagePath, packageName);
					}
				}
			}
			for(Class<?> candidate:classes) {
				parseClassCustomOneAnnotation(candidate);
 			}
			System.out.println(customOneClassMap.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 	/**
 	 * 加载包下的class
 	 * @param packagePath
 	 * @param packageName
 	 */
	private static void addClass(String packagePath, String packageName) {
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
			}
		});

		for (File file : files) {
			String fileName = file.getName();
			if (file.isFile()) {
				String className = fileName.substring(0, fileName.lastIndexOf("."));

				if (StringUtils.isNotEmpty(packageName)) {

					className = packageName + "." + className;
				}
				// 添加
				doAddClass(className);
			} else {
				// 子目录
				String subPackagePath = fileName;
				if (StringUtils.isNotEmpty(packagePath)) {
					subPackagePath = packagePath + "/" + subPackagePath;
				}

				String subPackageName = fileName;
				if (StringUtils.isNotEmpty(packageName)) {
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(subPackagePath, subPackageName);
			}
		}

	}

	private static void doAddClass(String className) {
		Class<?> cls = loadClass(className, false);
		classes.add(cls);
	}

	public static Class<?> loadClass(String className, boolean isInitialized) {

		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
			// Thread.currentThread().getContextClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return cls;
	}

	/**
	 * 解析类的注解
	 * 
	 * @param classa
	 */
	@SuppressWarnings({ "unchecked" })
	public static void parseClassCustomOneAnnotation(@SuppressWarnings("rawtypes") Class classa) {
		if (classa.getAnnotation(CustomOne.class) == null)
			return;
		// 获取类的注解
		CustomOne customOne = (CustomOne) classa.getAnnotation(CustomOne.class);
		if (customOne == null)
			return;
		// 获取方法的注解
		Method[] methods = classa.getMethods();
		for (Method method : methods) {
			parseMethodAnnotation(method, classa, customOne.name());
		}
	}

	/**
	 * 解析method的注解
	 * 
	 * @param method
	 */
	public static void parseMethodAnnotation(Method method, 
			@SuppressWarnings("rawtypes") Class classa,
			String classAnnotationName) {
		Annotation[] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			getCustomAnnotation(annotation, classa, method, classAnnotationName);
		}
	}

	/**
	 * 获取使用customOne注解的method,保存映射关系
	 * 
	 * @param annotation
	 * @param classa
	 */
	@SuppressWarnings("unchecked")
	public static void getCustomAnnotation(Annotation annotation, 
			@SuppressWarnings("rawtypes") Class classa,
			Method method, String classAnnotationName) {
		Class<CustomOne> customOne = CustomOne.class;
		if (annotation.annotationType().getName().equals(customOne.getName())) {
			StringBuffer customName = new StringBuffer(classAnnotationName);
			@SuppressWarnings("rawtypes")
			CustomParseEntity customEntity = new CustomParseEntity();
			customEntity.setClassF(classa);
			customEntity.setMethod(method);
			CustomOne custom = (CustomOne) annotation;
			customOneClassMap.put(customName.append(custom.name()).toString(), customEntity);
			System.out.println(method.getName());
		}
	}

	public static ClassLoader getClassLoader() {

		return Thread.currentThread().getContextClassLoader();
	}
}
