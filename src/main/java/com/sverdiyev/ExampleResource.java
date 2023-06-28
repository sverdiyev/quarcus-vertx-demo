package com.sverdiyev;

import com.sverdiyev.models.Fruit;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON) // default
@Consumes(MediaType.APPLICATION_JSON) // default
@ApplicationScoped // why is this needed?
@Path("/")
public class ExampleResource {

  @Inject
  public EventBus bus;


  @GET
  @Path("/hello")
  public String hello() {

    return "Hello from RESTEasy Reactive";
  }

  @GET
  @Path("/reactive-hello")
  public Uni<String> helloReactive() {

    bus.<String>request("greetings", "Sasha")
      .onItem().transform(Message::body);

    return Uni.createFrom().item("Hello Reactive");
  }

  @GET
  @Path("/fruits")
  public Uni<List<Fruit>> getAllFruits() {
    return Fruit.listAll(Sort.by("id"));
  }

  @GET
  @Path("/fruits/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Uni<Fruit> getFruit(String name) {
    return Fruit.findByName(name);
  }

  @POST
  @Path("/fruits")
  public void createFruit(Fruit fruit) { //is not reactive - does not work. does not save fruit

    Panache.withTransaction(fruit::persist).onItem()
      .invoke(() -> System.out.println("Saved"));
  }

  @POST
  @Path("/fruits2")
  public Uni<Void> createFruit2(Fruit fruit) { //reactive - this works. Saves fruit

    return Panache.withTransaction(fruit::persist).onItem()
      .invoke(() -> System.out.println("Saved")).replaceWithVoid();
  }
}
