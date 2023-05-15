package ru.namazov.asow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.namazov.asow.dto.OrderDTO;
import ru.namazov.asow.entity.Order;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);
}
