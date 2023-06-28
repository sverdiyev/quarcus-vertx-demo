package com.sverdiyev.vertx;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;

@SuppressWarnings("java:S106")
@ApplicationScoped // is that smth like @Component? So it is available in other parts of the app?
public class PeriodicFruitFetcher extends AbstractVerticle {


  @Override
  public Uni<Void> asyncStart() {
    final EventBus bus = vertx.eventBus();

    vertx.setPeriodic(Duration.ofSeconds(1).toMillis(), item -> {

      bus.<String>request("greeting", "sasha")
        .onItem().invoke(response -> System.out.println(response));

      bus.<String>request("greeting2", "sasha")
        .onItem().invoke(response -> System.out.println(response));


    });
    System.out.println("hello there from vertx verticle");
    return Uni.createFrom().voidItem();
  }


  @ConsumeEvent("greeting")
  public String consume(String name) {
    return name.toUpperCase();
  }

  @ConsumeEvent("greeting2")
  public Uni<String> consume2(String name) {
    return Uni.createFrom().item(name::toUpperCase);
  }

}
