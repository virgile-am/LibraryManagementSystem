package com.library.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Initialize a Transaction instance with sample data
        transaction = new Transaction(
                1,
                101,
                202,
                Date.valueOf("2024-01-01"),
                Date.valueOf("2024-01-15"),
                Date.valueOf("2024-01-10"),
                "Returned"
        );
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, transaction.getTransactionID());
        assertEquals(101, transaction.getBookID());
        assertEquals(202, transaction.getPatronID());
        assertEquals(Date.valueOf("2024-01-01"), transaction.getIssueDate());
        assertEquals(Date.valueOf("2024-01-15"), transaction.getDueDate());
        assertEquals(Date.valueOf("2024-01-10"), transaction.getReturnDate());
        assertEquals("Returned", transaction.getStatus());

        // Test setters
        transaction.setTransactionID(2);
        transaction.setBookID(102);
        transaction.setPatronID(203);
        transaction.setIssueDate(Date.valueOf("2024-02-01"));
        transaction.setDueDate(Date.valueOf("2024-02-15"));
        transaction.setReturnDate(Date.valueOf("2024-02-10"));
        transaction.setStatus("Overdue");

        assertEquals(2, transaction.getTransactionID());
        assertEquals(102, transaction.getBookID());
        assertEquals(203, transaction.getPatronID());
        assertEquals(Date.valueOf("2024-02-01"), transaction.getIssueDate());
        assertEquals(Date.valueOf("2024-02-15"), transaction.getDueDate());
        assertEquals(Date.valueOf("2024-02-10"), transaction.getReturnDate());
        assertEquals("Overdue", transaction.getStatus());
    }

    @Test
    public void testDefaultConstructor() {
        Transaction defaultTransaction = new Transaction();
        assertEquals(0, defaultTransaction.getTransactionID());
        assertEquals(0, defaultTransaction.getBookID());
        assertEquals(0, defaultTransaction.getPatronID());
        assertNull(defaultTransaction.getIssueDate());
        assertNull(defaultTransaction.getDueDate());
        assertNull(defaultTransaction.getReturnDate());
        assertNull(defaultTransaction.getStatus());
    }

    @Test
    public void testToString() {
        String expectedString = "Transaction{" +
                "transactionID=1" +
                ", bookID=101" +
                ", patronID=202" +
                ", issueDate=2024-01-01" +
                ", dueDate=2024-01-15" +
                ", returnDate=2024-01-10" +
                ", status='Returned'" +
                '}';
        assertEquals(expectedString, transaction.toString());
    }
}
