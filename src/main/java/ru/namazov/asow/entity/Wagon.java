package ru.namazov.asow.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "wagons")
@Setter
@Getter
@EqualsAndHashCode
public class Wagon {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wagon_passports_id", nullable = false)
    private WagonPassport wagonPassport;

    @Column(name = "position_number")
    private Long positionNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cargo_in_wagon",
            joinColumns = @JoinColumn(name = "wagons_id"),
            inverseJoinColumns = @JoinColumn(name = "cargos_id"))
    private List<Cargo> cargosList;

    @Column(name = "cargo_weight")
    private Long cargosWeight;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wagon_in_order",
            joinColumns = @JoinColumn(name = "wagons_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railways_id")
    private Railway railway;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
}
