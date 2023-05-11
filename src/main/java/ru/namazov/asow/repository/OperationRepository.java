package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
