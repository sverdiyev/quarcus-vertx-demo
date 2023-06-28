package com.sverdiyev;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Produces(MediaType.TEXT_PLAIN)
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
}
