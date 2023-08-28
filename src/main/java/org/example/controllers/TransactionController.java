package org.example.controllers;

import org.example.repository.transaction.TransactionRepository;
import org.example.repository.transaction.TransactionRepositoryImpl;
import org.example.service.transaction.TransactionService;
import org.example.service.transaction.TransactionServiceImpl;

public final class TransactionController {
    private final TransactionRepository transactionRepository = new TransactionRepositoryImpl();
    private final TransactionService transactionService = new TransactionServiceImpl(transactionRepository);
}