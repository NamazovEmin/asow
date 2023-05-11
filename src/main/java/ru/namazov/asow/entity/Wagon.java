package ru.namazov.asow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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

    @OneToOne(optional=false)
    @JoinColumn(name="wagon_passports_id")
    private WagonPassport wagonPassport;

    @Column(name = "serial_number")
    private Long serialNumber;

    @ManyToOne
    @JoinColumn(name="cargos_id")
    private Cargo cargo;

    @Column(name = "cargo_weight")
    private Long cargosWeight;

    @ManyToOne
    @JoinColumn(name = "trains_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "railways_id")
    private Railway railway;
}
