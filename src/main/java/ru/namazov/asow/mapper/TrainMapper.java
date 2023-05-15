package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.TrainDTO;
import ru.namazov.asow.entity.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainMapper {

    Order toEntity(TrainDTO trainDTO);

    TrainDTO toDTO(Order order);
}
