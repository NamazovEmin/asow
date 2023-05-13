package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.RailwayDTO;
import ru.namazov.asow.entity.Railway;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RailwayMapper {

    Railway toEntity(RailwayDTO railwayDTO);

    RailwayDTO toDTO(Railway railway);
}
