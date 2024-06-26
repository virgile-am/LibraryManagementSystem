package src.main.java.com.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import src.main.java.com.library.dao.BookDAO;
import src.main.java.com.library.dao.TransactionDAO;
import src.main.java.com.library.dao.UserDAO;
import src.main.java.com.library.model.Book;
import src.main.java.com.library.model.Transaction;
import src.main.java.com.library.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class TransactionController {

    @FXML
    private DatePicker issueDatePicker;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<Book> bookComboBox;

    @FXML
    private ComboBox<User> patronComboBox;

    @FXML
    private TableView<Transaction> transactionTableView;

    @FXML
    private ComboBox<String> statusComboBox;

    private final TransactionDAO transactionDAO;
    private final BookDAO bookDAO;
    private final UserDAO userDAO;

    public TransactionController() {
        transactionDAO = new TransactionDAO();
        bookDAO = new BookDAO();
        userDAO = new UserDAO();
    }

    @FXML
    private void initialize() {
        // Initialize the dropdowns and table view with data
        loadBooks();
        loadPatrons();
        loadTransactions();

        statusComboBox.setItems(FXCollections.observableArrayList("Returned", "Not Returned"));
    }

    @FXML
    private void handleAddTransaction() {
        try {
            Book selectedBook = bookComboBox.getSelectionModel().getSelectedItem();
            User selectedPatron = patronComboBox.getSelectionModel().getSelectedItem();
            LocalDate issueLocalDate = issueDatePicker.getValue();
            LocalDate dueLocalDate = dueDatePicker.getValue();
            String status = statusComboBox.getSelectionModel().getSelectedItem();

            if (selectedBook == null || selectedPatron == null || issueLocalDate == null || dueLocalDate == null || status == null) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields must be filled.");
                return;
            }

            Date issueDate = Date.valueOf(issueLocalDate);
            Date dueDate = Date.valueOf(dueLocalDate);

            Transaction transaction = new Transaction();
            transaction.setBookID(selectedBook.getBookID());
            transaction.setPatronID(selectedPatron.getUserID());
            transaction.setIssueDate(issueDate);
            transaction.setDueDate(dueDate);
            transaction.setStatus(status);

            transactionDAO.addTransaction(transaction);
            showAlert(Alert.AlertType.INFORMATION, "Transaction Added", "Transaction has been added successfully.");
            loadTransactions();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add transaction: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdateTransactionStatus() {
        Transaction selectedTransaction = transactionTableView.getSelectionModel().getSelectedItem();
        String newStatus = statusComboBox.getSelectionModel().getSelectedItem();

        if (selectedTransaction == null || newStatus == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please select a transaction and a new status.");
            return;
        }

        selectedTransaction.setStatus(newStatus);
        if ("Returned".equals(newStatus)) {
            selectedTransaction.setReturnDate(Date.valueOf(LocalDate.now()));
        } else {
            selectedTransaction.setReturnDate(null); // Clear return date if status is "Not Returned"
        }

        transactionDAO.updateTransaction(selectedTransaction);
        showAlert(Alert.AlertType.INFORMATION, "Status Updated", "Transaction status has been updated successfully.");
        loadTransactions();
    }

    private void loadBooks() {
        List<Book> books = bookDAO.getAllBooks();
        ObservableList<Book> bookList = FXCollections.observableArrayList(books);
        bookComboBox.setItems(bookList);
    }

    private void loadPatrons() {
        List<User> patrons = userDAO.getAllUsers();
        ObservableList<User> patronList = FXCollections.observableArrayList(patrons);
        patronComboBox.setItems(patronList);
    }

    private void loadTransactions() {
        transactionTableView.getItems().clear();
        transactionTableView.getItems().addAll(transactionDAO.getAllTransactions());
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
