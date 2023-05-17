package ru.namazov.asow.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import ru.namazov.asow.enums.OperationType;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "operations")
public class Operation {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OperationType type;

    @Column(name = "from_railway_id", nullable = false)
    private Long fromRailwayID;

    @Column(name = "to_railway_id", nullable = false)
    private Long whereRailwayID;

    @Column(name = "wagon_id", nullable = false)
    private String wagon;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    public Operation(OperationType type, Long fromRailwayID, Long whereRailwayID, String wagon) {
        this.type = type;
        this.fromRailwayID = fromRailwayID;
        this.whereRailwayID = whereRailwayID;
        this.wagon = wagon;
    }
}
