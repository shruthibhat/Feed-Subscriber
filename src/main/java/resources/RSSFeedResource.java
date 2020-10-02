package resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

@Path("/feeds")
public class RSSFeedResource {
    String auth = System.getenv("AUTH");

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getFeeds(){
        Client client = ClientBuilder.newClient();
        String path = String.format("http://fetchrss.com/api/v1/feed/list?auth=%s", auth);
        WebTarget target = client.
                target(path);
        String response = target.request().get(String.class);
        return response;
    }
}
