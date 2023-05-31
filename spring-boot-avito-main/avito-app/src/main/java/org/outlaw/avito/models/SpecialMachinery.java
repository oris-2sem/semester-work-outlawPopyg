package org.outlaw.avito.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialMachinery extends Car {
    private String machineryType; // тип техники
    private Integer bucketCapacity; // объем ковша
    private Integer maxMass;
    private String wheelFormula; // например, 4x4
}
