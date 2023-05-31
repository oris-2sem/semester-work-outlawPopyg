package org.outlaw.avito.models;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
public abstract class Car extends Vehicle {
    private Integer mileage;
    private String transmission;
    private String engineType;
    private Integer ownersCount;
    private Integer engineCapacity;
    private Integer enginePower;
    private String vin;

}
