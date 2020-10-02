import health.RSSFeedResourceHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.RSSFeedResource;

import javax.ws.rs.InternalServerErrorException;

public class RSSFeedApplication extends Application<RSSFeedConfiguration> {

    public static void main(String[] args){
        try {
            new RSSFeedApplication().run(args);
        } catch (Exception e) {
           throw new InternalServerErrorException("Error initializing application");
        }
    }
    public void run(RSSFeedConfiguration rssFeedConfiguration, Environment environment) throws Exception {
    final RSSFeedResource resource = new RSSFeedResource();
    environment.jersey().register(resource);
    environment.healthChecks().register("template", new RSSFeedResourceHealthCheck());
    }
}
