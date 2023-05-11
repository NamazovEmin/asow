package ru.namazov.asow.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ru.namazov.asow.enums.OperationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Table(name = "operations")
public class Operation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private OperationType type;

    @Column(name = "from_railway_id")
    private Long fromRailwayID;

    @Column(name = "where_railway_id")
    private Long whereRailwayID;

    // TODO: 11.05.2023 как сохранить не вагон id, а состояние обекта на данный момент?

    @Column(name = "wagon_id")
    private Long wagon_id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    public Operation(OperationType type, Long fromRailwayID, Long whereRailwayID, Long wagon_id) {
        this.type = type;
        this.fromRailwayID = fromRailwayID;
        this.whereRailwayID = whereRailwayID;
        this.wagon_id = wagon_id;
    }
}
