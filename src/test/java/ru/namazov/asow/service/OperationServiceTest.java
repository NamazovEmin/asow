package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void saveAll() {
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
        expectedOperation.setWagon("adad");
        expectedOperation.setId(1L);
        expectedOperationList.add(expectedOperation);

        Mockito.when(operationRepository.saveAll(operationList)).thenReturn(expectedOperationList);

        List<Operation> actualOperationList = operationService.saveAll(operationList);

        Assertions.assertEquals(expectedOperationList, actualOperationList);
    }

    @Test
    void findById() {
        Long id = 1L;

        Operation expectedOperation = new Operation();
        expectedOperation.setType(OperationType.RECEIVE);
        expectedOperation.setFromRailwayID(1L);
        expectedOperation.setWhereRailwayID(2L);
        expectedOperation.setWagon("adad");
        expectedOperation.setId(1L);


        Mockito.when(operationRepository.findById(id)).thenReturn(Optional.of(expectedOperation));

        Operation actualOperation = operationService.findById(id);

        Assertions.assertEquals(expectedOperation, actualOperation);
    }

    @Test
    void put() {
        Operation operation = new Operation();
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(100L);
        operation.setWhereRailwayID(200L);
        operation.setWagon("adad");
        operation.setId(1L);


        Operation expectedOperation = new Operation();
        expectedOperation.setType(OperationType.RECEIVE);
        expectedOperation.setFromRailwayID(1L);
        expectedOperation.setWhereRailwayID(2L);
        expectedOperation.setWagon("adad");
        expectedOperation.setId(1L);


        Mockito.when(operationRepository.findById(expectedOperation.getId())).thenReturn(Optional.of(operation));
        Mockito.when(operationRepository.save(expectedOperation)).thenReturn(expectedOperation);

        Operation actualOperation = operationService.put(expectedOperation);

        Assertions.assertEquals(expectedOperation, actualOperation);
    }

    @Test
    void deleteById() {
        Long id = 1L;

        Operation expectedOperation = new Operation();
        expectedOperation.setType(OperationType.RECEIVE);
        expectedOperation.setFromRailwayID(1L);
        expectedOperation.setWhereRailwayID(2L);
        expectedOperation.setWagon("adad");
        expectedOperation.setId(1L);


        Mockito.when(operationRepository.findById(id)).thenReturn(Optional.of(expectedOperation));
        Mockito.doNothing().when(operationRepository).deleteById(id);

        operationService.deleteById(id);

        Mockito.verify(operationRepository, Mockito.times(1)).findById(id);
        Mockito.verify(operationRepository, Mockito.times(1)).deleteById(id);
    }
}