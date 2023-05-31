package org.outlaw.avito.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
@SuperBuilder
@NoArgsConstructor
/** Что-то материальное **/
public abstract class Thing extends Ad {
    private Integer inStock;
}
