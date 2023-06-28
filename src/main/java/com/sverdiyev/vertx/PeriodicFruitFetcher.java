package com.sverdiyev.vertx;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped // is that smth like @Component? So it is available in other parts of the app?
public class PeriodicFruitFetcher extends AbstractVerticle {

  @Override
  public Uni<Void> asyncStart() {

    vertx.setPeriodic(Duration.ofSeconds(3).toMillis(), item -> System.out.println("hello there from vertx verticle"));
    return Uni.createFrom().voidItem();
  }
}
