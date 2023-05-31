package org.outlaw.avito.models;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public abstract class Vehicle extends Thing  {
    private String brand;
    private String model;
    private String country;
    private Integer passengersCount;
    private String condition;
    private Integer yearOfIssue;
    private String color;

}
