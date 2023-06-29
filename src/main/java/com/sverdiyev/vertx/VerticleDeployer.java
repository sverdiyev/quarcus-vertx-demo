package com.sverdiyev.vertx;

import io.quarkus.runtime.StartupEvent;
import io.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@SuppressWarnings("java:S106")
@ApplicationScoped
public class VerticleDeployer {


  @Inject
  Vertx vertx;

  public void init(@Observes StartupEvent startupEvent, Instance<AbstractVerticle> verticles) {

    System.out.println("deployment id in verticle deployer: " + vertx.getOrCreateContext().deploymentID());

    verticles.forEach(v -> vertx.deployVerticle(v).await().indefinitely()); //HOW TO DEPLOY MULTIPLE?
  }
}
