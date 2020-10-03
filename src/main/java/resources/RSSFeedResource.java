package resources;

import jdk.nashorn.internal.objects.annotations.Getter;
import model.CreateFeedRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/feeds")
public class RSSFeedResource {
    String auth = System.getenv("AUTH");

    @GET
    @Path("/createFeed")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFeed(@QueryParam("url") String url) throws Exception {
        if(auth == null || auth.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide the auth key!!");
        }
        if(url == null || url.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide a valid source url!!");
        }
        Client client = ClientBuilder.newClient();
        String path = String.format("http://fetchrss.com/api/v1/feed/create?auth=%s&url=%s", auth, url);
        WebTarget target = client.
                target(path);
        Response response = target.request().get();
        return response;
    }

    @GET
    @Path("/getFeedList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFeeds() throws Exception{
        if(auth == null || auth.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide the auth key!!");
        }
        Client client = ClientBuilder.newClient();
        String path = String.format("http://fetchrss.com/api/v1/feed/list?auth=%s", auth);
        WebTarget target = client.
                target(path);
        Response response = target.request().get();
        return response;
    }

    @DELETE
    @Path("/deleteFeed")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFeed(@QueryParam("id") String id) throws Exception {
        if(auth == null || auth.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide the auth key!!");
        }

        if(id == null || id.equalsIgnoreCase("")){
            throw new BadRequestException("Please provide the auth key!!");
        }

        Client client = ClientBuilder.newClient();
        String path = String.format("http://fetchrss.com/api/v1/feed/delete?auth=%s&id=%s", auth, id);
        WebTarget target = client.
                target(path);
        Response response = target.request().get();
        return response;
    }
}
