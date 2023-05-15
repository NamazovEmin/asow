package ru.namazov.asow.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "wagons")
@Setter
@Getter
public class Wagon {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name="wagon_passports_id", nullable = false)
    private WagonPassport wagonPassport;

    @Column(name = "serial_number")
    private Long serialNumber;

    @ManyToMany
    @JoinTable(
            name = "cargos_in_wagon",
            joinColumns = @JoinColumn(name = "wagons_id"),
            inverseJoinColumns = @JoinColumn(name = "cargos_id"))
    private List<Cargo> cargosList;

    @Column(name = "cargo_weight")
    private Long cargosWeight;

    @ManyToMany
    @JoinTable(
            name = "wagon_ir_order",
            joinColumns = @JoinColumn(name = "wagons_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private List<Order> order;

    @ManyToOne
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

    public Long getId() {
        return id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }
}
