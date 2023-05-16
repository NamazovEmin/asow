package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.StationDTO;
import ru.namazov.asow.entity.Station;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StationMapper {

    Station toEntity(StationDTO stationDTO);

    @Mapping(target = "id", ignore = true)
    Station toPOSTEntity(StationDTO stationDTO);

    StationDTO toDTO(Station station);
}
