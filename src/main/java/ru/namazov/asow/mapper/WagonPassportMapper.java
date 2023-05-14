package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.entity.WagonPassport;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WagonPassportMapper {
    @Mapping(target = "id", ignore = true)
    WagonPassport toEntity(WagonPassportDTO wagonPassportDTO);

    WagonPassportDTO toDTO(WagonPassport wagonPassport);
}
