package ru.namazov.asow.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "wagons")
@Setter
public class Wagon {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne()
    @JoinColumn(name="wagon_passports_id")
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

    @ManyToOne
    @JoinColumn(name = "trains_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "railways_id")
    private Railway railway;

    public Long getId() {
        return id;
    }

    public Long getSerialNumber() {
        return serialNumber;
    }
}
