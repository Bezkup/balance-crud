package it.dreamteam.balance.repository;

import it.dreamteam.balance.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByCategoryId(Long category_id);
    List<Transaction> findTransactionsByTransactionDateBetween(LocalDate startDate, LocalDate endDate);
}
