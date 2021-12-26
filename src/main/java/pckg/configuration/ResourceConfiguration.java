package pckg.configuration;

import org.glassfish.jersey.server.ResourceConfig;

public class ResourceConfiguration extends ResourceConfig {
	
	public ResourceConfiguration() {
		packages("pckg");
	}

}
