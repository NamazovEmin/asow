package ru.namazov.asow.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.entity.Wagon;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WagonMapper {

    Wagon toEntity(WagonDTO wagonDTO);

//    @Mapping(target = "id", ignore = true)
//    Wagon toPOSTEntity(WagonDTO wagonDTO);

    WagonDTO toDTO(Wagon wagon);

    @Mapping(target = "id", ignore = true)
    List<Wagon> toEntity(List<WagonDTO> wagonDTOList);
}
