package ru.namazov.asow.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.OperationDTO;
import ru.namazov.asow.entity.Operation;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OperationMapper {

    Operation toEntity(OperationDTO operationDTO);

    @Mapping(target = "id", ignore = true)
    Operation toPOSTEntity(OperationDTO operationDTO);

    OperationDTO toDTO(Operation operation);

    List<OperationDTO> toDTO(List<Operation> operationList);
}
