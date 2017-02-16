package com.opensource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
@Service
@Path("/hello")
public class App 
{
    public Map<String,Integer> responseMap= Maps.newHashMap();
    public List<HttpServletResponse> responseList= Lists.newArrayList();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/1")
    public void customerServiceCheckEvent(@Suspended AsyncResponse asyncResponse, @Context HttpServletResponse response) {
        Integer count=responseMap.get(response.toString());
        responseList.add(response);
        if(count!=null){
            responseMap.put(response.toString(),count+1);
        }else{
            responseMap.put(response.toString(),1);
        }
        responseMap.forEach((k,v)->{
            if(v>1){
                System.out.println(k+","+v);
            }
        });
        asyncResponse.setTimeout(1, TimeUnit.SECONDS);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/2")
    public Response customerServiceCheckEvent() {
        return Response.ok(responseMap).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/3")
    public Response customer() {
        return Response.ok("OK").build();
    }
}
