package ru.namazov.asow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.exception.NotFoundException;
import ru.namazov.asow.repository.OperationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;

    public List<Operation> saveAll(List<Operation> operationList) {
        return operationRepository.saveAll(operationList);
    }

    public Operation findById(Long id) {
        return operationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Operation with id %s not found", id)));
    }

    public Operation put(Operation operation) {
        operationRepository.findById(operation.getId())
                .orElseThrow(() -> new NotFoundException(String.format("current Operation with id %s not found to update", operation.getId())));
        return operationRepository.save(operation);
    }

    public void deleteById(Long id) {
        operationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Operation with id %s not found", id)));
        operationRepository.deleteById(id);
    }
}
