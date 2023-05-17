package ru.namazov.asow.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ru.namazov.asow.enums.WagonType;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "wagon_passports")
@Setter
@Getter
@EqualsAndHashCode
public class WagonPassport {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", nullable = false, unique = true)
    private Long serialNumber;

    @Column(name = "wagon_type_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private WagonType wagonType;

    @Column(name = "container_weight")
    private Long containerWeight;

    @Column(name = "carrying_capacity")
    private Long carryingCapacity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
}
