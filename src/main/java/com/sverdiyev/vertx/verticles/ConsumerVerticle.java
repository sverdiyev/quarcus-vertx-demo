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
    System.out.println("deployment id in consumer verticle: " + vertx);
    return Uni.createFrom().voidItem();
  }

  @ConsumeEvent(ProviderVerticle.ADDRESS_ONE)
  public String consume(String name) {
    System.out.println("consumer 1 runs");
    return name.toUpperCase();
  }

  @ConsumeEvent(ProviderVerticle.ADDRESS_TWO)
  public Uni<String> consume2(String name) {
    System.out.println("consumer 2 runs");
    return Uni.createFrom().item(name::toUpperCase);
  }
}
