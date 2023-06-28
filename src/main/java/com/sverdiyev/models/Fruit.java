package com.sverdiyev.models;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Fruit extends PanacheEntity {


  @Column(length = 64, unique = true, nullable = false)
  public String name;

  public static Uni<Fruit> findByName(String name) {
    return find("name", name).firstResult();
  }

}
