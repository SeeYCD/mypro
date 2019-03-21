package myproject.tools;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ClassLoadT {
	public static Set doFindAllClassPathResources(String path)
	        throws IOException{
	        Set result = new LinkedHashSet(16);
	        ClassLoader cl = null;
	        URL url;
	        Enumeration resourceUrls = cl == null ? ClassLoader.getSystemResources(path) : cl.getResources(path);
	        while(resourceUrls.hasMoreElements()){
	        	url = (URL)resourceUrls.nextElement();
	        	result.add(convertClassLoaderURL(url));
	        }
  	        return result;
	    }
	protected static Resource convertClassLoaderURL(URL url){
        return new UrlResource(url);
    }
	
	public static Resource[] classLoader(String path) throws IOException{
		ResourcePatternResolver rp=new PathMatchingResourcePatternResolver();
		Resource resources[] =rp.getResources(path);
		return resources;
		
	}
	
}
