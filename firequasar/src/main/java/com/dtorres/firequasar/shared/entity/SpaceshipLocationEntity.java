package com.dtorres.firequasar.shared.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "spaceship_location")
public class SpaceshipLocationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name="name")
  private String name;

  @Column(name="coordinate_x")
  private Double coordinateX;

  @Column(name="coordinate_y")
  private Double coordinateY;

  @Column(name="distance")
  private Double distance;

  @Column(name="messages")
  private String messages;

  public SpaceshipLocationEntity(){}

  public SpaceshipLocationEntity(String name, Double coordinateX, Double coordinateY) {
    this.setName(name);
    this.setCoordinateX(coordinateX);
    this.setCoordinateY(coordinateY);
  }

  public SpaceshipLocationEntity(String name, Double distance, String messages) {
    this.setName(name);
    this.setDistance(distance);
    this.setMessages(messages);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getCoordinateX() {
    return coordinateX;
  }

  public void setCoordinateX(Double coordinateX) {
    this.coordinateX = coordinateX;
  }

  public Double getCoordinateY() {
    return coordinateY;
  }

  public void setCoordinateY(Double coordinateY) {
    this.coordinateY = coordinateY;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public String getMessages() {
    return messages;
  }

  public void setMessages(String messages) {
    this.messages = messages;
  }
}
