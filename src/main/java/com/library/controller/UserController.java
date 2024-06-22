package src.main.java.com.library.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.main.java.com.library.dao.PatronDAO;
import src.main.java.com.library.dao.UserDAO;
import src.main.java.com.library.model.Patron;
import src.main.java.com.library.model.User;

public class UserController {

    // User UI elements
    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, Integer> userIdColumn;

    @FXML
    private TableColumn<User, String> usernameColumn;

    @FXML
    private TableColumn<User, Integer> roleIdColumn;

    @FXML
    private TextField userIdField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField roleIdField;

    // Patron UI elements
    @FXML
    private TableView<Patron> patronTable;

    @FXML
    private TableColumn<Patron, Integer> patronIdColumn;

    @FXML
    private TableColumn<Patron, String> nameColumn;

    @FXML
    private TableColumn<Patron, String> emailColumn;

    @FXML
    private TableColumn<Patron, String> phoneColumn;

    @FXML
    private TableColumn<Patron, String> addressColumn;

    @FXML
    private TextField patronIdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField addressField;

    private UserDAO userDAO;
    private PatronDAO patronDAO;
    private ObservableList<User> userList;
    private ObservableList<Patron> patronList;

    public void initialize() {
        userDAO = new UserDAO();
        patronDAO = new PatronDAO();

        // Initialize user table
        loadUserTable();
        setupUserTableListener();

        // Initialize patron table
        loadPatronTable();
        setupPatronTableListener();
    }

    // User table methods
    private void loadUserTable() {
        userList = FXCollections.observableArrayList(userDAO.getAllUsers());
        userTable.setItems(userList);

        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIDProperty().asObject());
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        roleIdColumn.setCellValueFactory(cellData -> cellData.getValue().roleIDProperty().asObject());
    }

    private void setupUserTableListener() {
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserDetails(newValue));
    }

    private void showUserDetails(User user) {
        if (user != null) {
            userIdField.setText(String.valueOf(user.getUserID()));
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            roleIdField.setText(String.valueOf(user.getRoleID()));
        } else {
            clearUserFields();
        }
    }

    @FXML
    private void handleAddUser() {
        try {
            int userId = Integer.parseInt(userIdField.getText());
            String username = usernameField.getText();
            String password = passwordField.getText();
            int roleId = Integer.parseInt(roleIdField.getText());

            User newUser = new User(userId, username, password, roleId);
            userDAO.addUser(newUser);

            userList.add(newUser);
            showSuccessAlert("User added successfully!");
            clearUserFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter valid numbers for UserID and RoleID.");
        }
    }

    @FXML
    private void handleUpdateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                String username = usernameField.getText();
                String password = passwordField.getText();
                int roleId = Integer.parseInt(roleIdField.getText());

                User updatedUser = new User(userId, username, password, roleId);
                userDAO.updateUser(updatedUser);

                userList.set(userList.indexOf(selectedUser), updatedUser);
                showSuccessAlert("User updated successfully!");
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter valid numbers for UserID and RoleID.");
            }
        } else {
            showAlert("No Selection", "Please select a user to update.");
        }
    }

    @FXML
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            int userId = selectedUser.getUserID();
            userDAO.deleteUser(userId);
            userList.remove(selectedUser);
            showSuccessAlert("User deleted successfully!");
        } else {
            showAlert("No Selection", "Please select a user to delete.");
        }
    }

    // Patron table methods
    private void loadPatronTable() {
        patronList = FXCollections.observableArrayList(patronDAO.getAllPatrons());
        patronTable.setItems(patronList);

        patronIdColumn.setCellValueFactory(cellData -> cellData.getValue().patronIDProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
    }

    private void setupPatronTableListener() {
        patronTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPatronDetails(newValue));
    }

    private void showPatronDetails(Patron patron) {
        if (patron != null) {
            patronIdField.setText(String.valueOf(patron.getPatronID()));
            nameField.setText(patron.getName());
            emailField.setText(patron.getEmail());
            phoneField.setText(patron.getPhone());
            addressField.setText(patron.getAddress());
        } else {
            clearPatronFields();
        }
    }

    @FXML
    private void handleAddPatron() {
        try {
            int patronId = Integer.parseInt(patronIdField.getText());
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();

            Patron newPatron = new Patron(patronId, name, email, phone, address);
            patronDAO.addPatron(newPatron);

            patronList.add(newPatron);
            showSuccessAlert("Patron added successfully!");
            clearPatronFields();
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter valid numbers for Patron ID.");
        }
    }

    @FXML
    private void handleUpdatePatron() {
        Patron selectedPatron = patronTable.getSelectionModel().getSelectedItem();
        if (selectedPatron != null) {
            try {
                int patronId = Integer.parseInt(patronIdField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();

                Patron updatedPatron = new Patron(patronId, name, email, phone, address);
                patronDAO.updatePatron(updatedPatron);

                patronList.set(patronList.indexOf(selectedPatron), updatedPatron);
                showSuccessAlert("Patron updated successfully!");
            } catch (NumberFormatException e) {
                showAlert("Invalid input", "Please enter valid numbers for Patron ID.");
            }
        } else {
            showAlert("No Selection", "Please select a patron to update.");
        }
    }

    @FXML
    private void handleDeletePatron() {
        Patron selectedPatron = patronTable.getSelectionModel().getSelectedItem();
        if (selectedPatron != null) {
            int patronId = selectedPatron.getPatronID();
            patronDAO.deletePatron(patronId);
            patronList.remove(selectedPatron);
            showSuccessAlert("Patron deleted successfully!");
        } else {
            showAlert("No Selection", "Please select a patron to delete.");
        }
    }

    // Helper methods
    private void clearUserFields() {
        userIdField.clear();
        usernameField.clear();
        passwordField.clear();
        roleIdField.clear();
    }

    private void clearPatronFields() {
        patronIdField.clear();
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleClose() {
        Stage stage = (Stage) userIdField.getScene().getWindow();
        stage.close();
    }
}
