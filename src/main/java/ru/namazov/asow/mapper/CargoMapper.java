package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.CargoDTO;
import ru.namazov.asow.entity.Cargo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CargoMapper {

    Cargo toEntity(CargoDTO cargoDTO);

    @Mapping(target = "id", ignore = true)
    Cargo toPOSTEntity(CargoDTO cargoDTO);

    CargoDTO toDTO(Cargo cargo);
}
