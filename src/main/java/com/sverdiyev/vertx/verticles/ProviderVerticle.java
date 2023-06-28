package com.sverdiyev.vertx.verticles;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;

@SuppressWarnings("java:S106")
@ApplicationScoped // is that smth like @Component? So it is available in other parts of the app?
public class ProviderVerticle extends AbstractVerticle {

  @Override
  public Uni<Void> asyncStart() {
    final EventBus bus = vertx.eventBus();

    //greeting 1
    vertx.setPeriodic(Duration.ofSeconds(1).toMillis(), item -> {
      bus.<String>request("greeting", "sasha")
        .onItem().invoke(response -> System.out.println(response.body()));

      //greeting 2
      bus.<String>request("greeting2", "sasha")
        .onItem().invoke(response -> System.out.println(response.body()));
    });

    System.out.println("hello there from vertx verticle");
    return Uni.createFrom().voidItem();
  }
}