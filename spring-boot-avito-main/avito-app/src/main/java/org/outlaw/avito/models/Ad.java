package org.outlaw.avito.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(exclude = {"likes", "author"})
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PersonalItem.class, name = "personalItem"),
        @JsonSubTypes.Type(value = PassengerCar.class, name = "passengerCar"),
        @JsonSubTypes.Type(value = SpecialMachinery.class, name = "specialMachinery")
})
public abstract class Ad {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String title;
    private Integer price;
    @Builder.Default
    private Timestamp createdAt = Timestamp.from(Instant.now());
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @ManyToMany(mappedBy = "favoriteAds")
    private Set<User> likes;

    @OneToMany(mappedBy = "ad")
    private List<AdImage> images;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private AdCategory category;
}
