package ru.namazov.asow.service;

import org.springframework.stereotype.Service;

import ru.namazov.asow.repository.OperationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OperationService {

    private final OperationRepository operationRepository;
}
