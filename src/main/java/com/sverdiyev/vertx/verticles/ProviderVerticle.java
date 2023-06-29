package com.sverdiyev.vertx.verticles;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;

@SuppressWarnings("java:S106")
@ApplicationScoped
public class ProviderVerticle extends AbstractVerticle {


  public static final String ADDRESS_ONE = "address_one";
  public static final String ADDRESS_TWO = "address_two";

  @Override
  public Uni<Void> asyncStart() {
    final EventBus bus = vertx.eventBus();

    vertx.setPeriodic(Duration.ofSeconds(1).toMillis(), item -> {

      //from guide
      bus.<String>request(ADDRESS_ONE, "Sasha")
        .onItem().transform(Message::body)
        .subscribe().with(body -> System.out.println("Received: " + body));

      //greeting 2
      bus.<String>request(ADDRESS_TWO, "sasha")
        .subscribe().with(response -> System.out.println(response.body()));

    });

    System.out.println("deployment id in provider verticle: " + vertx.getOrCreateContext().deploymentID());
    return Uni.createFrom().voidItem();
  }
}