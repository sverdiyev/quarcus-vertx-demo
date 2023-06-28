package com.sverdiyev.vertx;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.eventbus.EventBus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Duration;
import org.eclipse.microprofile.context.ManagedExecutor;

@SuppressWarnings("java:S106")
@ApplicationScoped // is that smth like @Component? So it is available in other parts of the app?
public class PeriodicFruitFetcher extends AbstractVerticle {

  //  public static final String ADDRESS = "showMeTheTime_address";
  @Inject
  ManagedExecutor executor;

//  @ConsumeEvent(ADDRESS)
////  @ConsumeEvent(value = ADDRESS)
//  private Uni<String> consumer(String someText) {
//
//    return Uni.createFrom().item("ITS PARTY TIME" + someText);
//  }

  @Override
  public Uni<Void> asyncStart() {
    final EventBus bus = vertx.eventBus();

    vertx.setPeriodic(Duration.ofSeconds(1).toMillis(), item -> {

      //sending a message and awaiting response ASYNC
//      bus.<String>request(ADDRESS, "hello there").onItem()
//        .invoke(response -> System.out.println(response));

      bus.<String>request("greeting", "sasha")
        .onItem().invoke(response -> System.out.println(response));

    });
    System.out.println("hello there from vertx verticle");
    return Uni.createFrom().voidItem();
  }

//  @ConsumeEvent(ADDRESS)
//  private String consumerBlocking(String someText) {
//
//    System.out.println("do we receive it? ");
//    return "ITS PARTY TIME" + someText;
//  }

  @ConsumeEvent("greeting")
  public String consume(String name) {
    return name.toUpperCase();
  }

  @ConsumeEvent("greeting2")
  public Uni<String> consume2(String name) {
    return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
  }

}
