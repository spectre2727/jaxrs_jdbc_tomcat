package jaxrs_tomcat;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import jaxrs_tomcat.configuration.ResourceConfiguration;

public class Main {
	
	private static Tomcat tomcat;
	private static String contextPath = "/tom";
	private static String docBase = new File(".").getAbsolutePath();
	private static String pattern = "/*";
	
	public static void stop() throws LifecycleException {
		tomcat.stop();
	}
	
	public static void main(String[] args) throws LifecycleException {
		tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector();
		Context context = tomcat.addWebapp(contextPath, docBase);
		ServletContainer servletContainer = new ServletContainer(new ResourceConfiguration());
		tomcat.addServlet(contextPath, "mainServlet", servletContainer);
		context.addServletMappingDecoded(pattern, "mainServlet");
		tomcat.start();
	}
	
}
