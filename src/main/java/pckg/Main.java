package pckg;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

import pckg.configuration.ResourceConfiguration;

public class Main {
		
	public static void main(String[] args) throws LifecycleException {
		// Start a new Tomcat
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);
		tomcat.getConnector();
		
		// Add an unique context for the webapp, and webapp to Tomcat
		Context context = tomcat.addWebapp("/spec01", new File(".").getAbsolutePath());
		
		// Add the servlet to the webapp context defined previously
		ServletContainer servletContainer = new ServletContainer(new ResourceConfiguration());
		tomcat.addServlet("/spec01", "mainServlet", servletContainer);
		context.addServletMappingDecoded("/*", "mainServlet");
		
		// Run the server
		tomcat.start();
	}
	
}
