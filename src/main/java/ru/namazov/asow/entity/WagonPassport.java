package ru.namazov.asow.entity;

import ru.namazov.asow.enums.WagonType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "wagon_passports")
@Setter
public class WagonPassport {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private Long number;

    @Column(name = "type", nullable = false)
    private WagonType wagonType;

    @Column(name = "container_weight")
    private Long containerWeight;

    @Column(name = "carrying_capacity")
    private Long carryingCapacity;
}
