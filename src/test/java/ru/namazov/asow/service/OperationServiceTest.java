package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.enums.OperationType;
import ru.namazov.asow.repository.OperationRepository;

class OperationServiceTest {

    private final OperationRepository operationRepository = Mockito.mock(OperationRepository.class);
    private final OperationService operationService = new OperationService(operationRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        List<Operation> operationList = new ArrayList<>();
        Operation operation = new Operation();
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(1L);
        operation.setWhereRailwayID(2L);
        operation.setWagon("adad");
        operationList.add(operation);

        List<Operation> expectedOperationList = new ArrayList<>();
        Operation expectedOperation = new Operation();
        expectedOperation.setType(OperationType.RECEIVE);
        expectedOperation.setFromRailwayID(1L);
        expectedOperation.setWhereRailwayID(2L);
        operation.setWagon("adad");
        expectedOperation.setId(1L);
        expectedOperationList.add(expectedOperation);

        Mockito.when(operationRepository.saveAll(operationList)).thenReturn(expectedOperationList);

        List<Operation> actualOperationList = operationService.saveAll(operationList);

        Assertions.assertEquals(expectedOperation, actualOperationList);
    }
}