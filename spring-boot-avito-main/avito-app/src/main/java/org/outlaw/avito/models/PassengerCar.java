package org.outlaw.avito.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerCar extends Car {
    private String generation;
    private String modification;
    private String driveUnit; // привод
    private String equipment; // комплектация
    private String bodyType; // тип кузова

    @Enumerated(EnumType.STRING)
    private SteeringWheelType steeringWheel; // руль (правый/левый)

    public enum SteeringWheelType { LEFT, RIGHT }
}
