package com.sverdiyev.vertx.test_event_bus;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import jakarta.enterprise.context.ApplicationScoped;

@SuppressWarnings("java:S106")
@ApplicationScoped
public class TestConsumer extends AbstractVerticle {


  @Override
  public Uni<Void> asyncStart() {
    System.out.println("deployment id in test consumer verticle: " + vertx.getOrCreateContext().deploymentID());

    return Uni.createFrom().voidItem();
  }

  @ConsumeEvent(TestController.TEST_ADDRESS)
  public String greeting(String name) {

    System.out.println("consumer");
    return "Hello wow" + name;
  }

}