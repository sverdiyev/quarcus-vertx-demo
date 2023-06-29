package com.sverdiyev.vertx.testEventBus;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TestConsumer extends AbstractVerticle {

  @Inject
  Vertx vertx;

  @Override
  public Uni<Void> asyncStart() {
    System.out.println("test consumer verticle started");

    System.out.println("deployment id in test consumer verticle: " + vertx.getOrCreateContext().deploymentID());

    return Uni.createFrom().voidItem();
  }

  @ConsumeEvent("greeting")
  public String greeting(String name) {

    System.out.println("consumer");
    return "Hello wow" + name;
  }

}