package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.TrainDTO;
import ru.namazov.asow.entity.Train;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrainMapper {

    Train toEntity(TrainDTO trainDTO);

    TrainDTO toDTO(Train train);

}
