package com.sverdiyev.vertx.verticles;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import jakarta.enterprise.context.ApplicationScoped;

@SuppressWarnings("java:S106")
@ApplicationScoped
public class ConsumerVerticle extends AbstractVerticle {

  @Override
  public Uni<Void> asyncStart() {
    System.out.println("consumer deployed");
    return Uni.createFrom().voidItem();
  }

  @ConsumeEvent("greeting")
  public String consume(String name) {
    System.out.println("consumer 1 runs");
    return name.toUpperCase();
  }

  @ConsumeEvent("greeting2")
  public Uni<String> consume2(String name) {
    System.out.println("consumer 2 runs");
    return Uni.createFrom().item(name::toUpperCase);
  }
}
