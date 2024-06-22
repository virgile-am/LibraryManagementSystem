package src.main.java.com.library.model;

import java.sql.Date;

public class Transaction {
    private int transactionID;
    private int bookID;
    private int patronID;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private String status;

    // Constructor
    public Transaction(int transactionID, int bookID, int patronID, Date issueDate, Date dueDate, Date returnDate, String status) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.patronID = patronID;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public Transaction() {}

    // Getters and Setters
    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getPatronID() {
        return patronID;
    }

    public void setPatronID(int patronID) {
        this.patronID = patronID;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", bookID=" + bookID +
                ", patronID=" + patronID +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status='" + status + '\'' +
                '}';
    }
}
