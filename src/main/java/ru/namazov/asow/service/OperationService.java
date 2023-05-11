package ru.namazov.asow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.repository.OperationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public List<Operation> saveAll(List<Operation> operationList) {
        return operationRepository.saveAll(operationList);
    }
}
