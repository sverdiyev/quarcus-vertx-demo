package com.sverdiyev;

import com.sverdiyev.models.Fruit;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON) // default
@Consumes(MediaType.APPLICATION_JSON) // default
//@ApplicationScoped // why is this needed?
@Path("/elo")
public class ExampleResource {

  @GET
  @Path("/hello")
  public String hello() {
    return "Hello from RESTEasy Reactive";
  }

  @GET
  @Path("/reactive-hello")
  public Uni<String> helloReactive() {
    return Uni.createFrom().item("Hello Reactive");
  }

  @GET
  @Path("/fruits")
  public Uni<List<Fruit>> getAllUsers() {
    return Fruit.listAll(Sort.by("id"));
  }

  @GET
  @Path("/specific-fruit")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Fruit> getAllUsers3() {
    return Fruit.findByName("s");
  }
}
