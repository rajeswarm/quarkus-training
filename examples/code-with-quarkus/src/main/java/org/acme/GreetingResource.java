package org.acme;

import java.time.Duration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        return Uni.createFrom().item("Non Blocking Hello from Quarkus !!!");
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Blocking
    @Path("/blocking")
    public Uni<String> helloBlocking() {
        return Uni.createFrom().item("Blocking Hello from Quarkus!!!").onItem().delayIt().by(Duration.ofSeconds(2));
    }
}