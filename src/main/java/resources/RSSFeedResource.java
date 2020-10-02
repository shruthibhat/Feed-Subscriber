package resources;

import javax.ws.rs.*;
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
    public String getFeeds() throws Exception{
        if(auth == null || auth.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide the auth key!!");
        }
        Client client = ClientBuilder.newClient();
        String path = String.format("http://fetchrss.com/api/v1/feed/list?auth=%s", auth);
        WebTarget target = client.
                target(path);
        String response = target.request().get(String.class);
        return response;
    }
}
