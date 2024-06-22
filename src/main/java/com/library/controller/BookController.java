package src.main.java.com.library.controller;

import src.main.java.com.library.dao.BookDAO;
import src.main.java.com.library.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class BookController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField publisherField;
    @FXML
    private TextField yearField;
    @FXML
    private TextField isbnField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField quantityField;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> categoryColumn;
    @FXML
    private TableColumn<Book, Integer> quantityColumn;
    @FXML
    private TableColumn<Book, Void> editColumn;
    @FXML
    private TableColumn<Book, Void> deleteColumn;

    private BookDAO bookDAO;

    @FXML
    private void initialize() {
        bookDAO = new BookDAO();
        loadBooks();

        // Configure TableView columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().bookIDProperty().asObject());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        publisherColumn.setCellValueFactory(cellData -> cellData.getValue().publisherProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        addEditButtonToTable();
        addDeleteButtonToTable();

        // Add listener for selection changes
        bookTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showBookDetails(newValue));
    }

    private void addEditButtonToTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            showBookDetails(book);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        editColumn.setCellFactory(cellFactory);
    }

    private void addDeleteButtonToTable() {
        Callback<TableColumn<Book, Void>, TableCell<Book, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Book, Void> call(final TableColumn<Book, Void> param) {
                final TableCell<Book, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction(event -> {
                            Book book = getTableView().getItems().get(getIndex());
                            bookDAO.deleteBook(book.getBookID());
                            loadBooks();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        deleteColumn.setCellFactory(cellFactory);
    }

    @FXML
    private void handleAddBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            String isbn = isbnField.getText();
            String category = categoryField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            // Validate ISBN length
            if (isbn.length() > 20) {
                showError("ISBN cannot exceed 20 characters.");
                return;
            }

            Book newBook = new Book(title, author, publisher, year, isbn, category, quantity);
            bookDAO.addBook(newBook);
            loadBooks();
        } catch (NumberFormatException e) {
            showError("Year and Quantity must be valid numbers.");
        }
    }

    @FXML
    private void handleUpdateBook() {
        try {
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            if (selectedBook == null) {
                showError("No book selected.");
                return;
            }

            String title = titleField.getText();
            String author = authorField.getText();
            String publisher = publisherField.getText();
            int year = Integer.parseInt(yearField.getText());
            String isbn = isbnField.getText();
            String category = categoryField.getText();
            int quantity = Integer.parseInt(quantityField.getText());

            // Validate ISBN length
            if (isbn.length() > 20) {
                showError("ISBN cannot exceed 20 characters.");
                return;
            }

            selectedBook.setTitle(title);
            selectedBook.setAuthor(author);
            selectedBook.setPublisher(publisher);
            selectedBook.setYear(year);
            selectedBook.setIsbn(isbn);
            selectedBook.setCategory(category);
            selectedBook.setQuantity(quantity);

            bookDAO.updateBook(selectedBook);
            loadBooks();
        } catch (NumberFormatException e) {
            showError("Year and Quantity must be valid numbers.");
        }
    }

    private void loadBooks() {
        bookTable.getItems().clear();
        bookTable.getItems().addAll(bookDAO.getAllBooks());
    }

    private void showBookDetails(Book book) {
        if (book != null) {
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            publisherField.setText(book.getPublisher());
            yearField.setText(Integer.toString(book.getYear()));
            isbnField.setText(book.getIsbn());
            categoryField.setText(book.getCategory());
            quantityField.setText(Integer.toString(book.getQuantity()));
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
