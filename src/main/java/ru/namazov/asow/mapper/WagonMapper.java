package ru.namazov.asow.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.entity.Wagon;

@Mapper(componentModel = "spring", uses = {WagonPassportMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WagonMapper {

    Wagon toEntity(WagonDTO wagonDTO);

    WagonDTO toDTO(Wagon wagon);

    List<Wagon> toEntity(List<WagonDTO> wagonDTOList);
}
