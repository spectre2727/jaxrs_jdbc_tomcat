package pckg;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import pckg.configuration.ResourceConfiguration;

public class Main {
	
	private static Tomcat tomcat = new Tomcat();
	private static String contextPath = "/cp";
	private static String docBase = new File(".").getAbsolutePath();
	private static String pattern = "/*";
	
	public static void main(String[] args) throws LifecycleException {
		tomcat.setPort(8080);
		tomcat.getConnector();
		
		Context context = tomcat.addWebapp(contextPath, docBase);
		ServletContainer servletContainer = new ServletContainer(new ResourceConfiguration());
		tomcat.addServlet(contextPath, "mainServlet", servletContainer);
		context.addServletMappingDecoded(pattern, "mainServlet");
		
		tomcat.start();
	}
	
}
