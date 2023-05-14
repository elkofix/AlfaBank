package com.example.demo.controller;

import com.example.demo.HelloApplication;
import com.example.demo.model.Transaction;
import com.example.demo.model.TransactionList;
import com.example.demo.model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Locale;

public class AddTransactionController {

    @FXML
    private TextField valueTransactionTF;

    @FXML
    private Label labelAlert;
    @FXML
    private TextField descTransactionTF;

    public TransactionList getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }

    private TransactionList transactionList;

    public void setTypeTransactionCB(ComboBox<Type> typeTransactionCB) {
        this.typeTransactionCB = typeTransactionCB;
    }

    @FXML
    private ComboBox<Type> typeTransactionCB;
    @FXML
    private DatePicker dateTransactionDP;

    public AddTransactionController() {
    }

    public void initialize(){
        valueTransactionTF.setOnKeyTyped(event->{
            labelAlert.setText("");
        });
        descTransactionTF.setOnKeyTyped(event->{
            labelAlert.setText("");
        });
        typeTransactionCB.setOnAction(event->{
            labelAlert.setText("");
        });
        dateTransactionDP.setOnAction(event->{
            labelAlert.setText("");
        });
    }

    @FXML
    public void onAddTransaction(ActionEvent event) {
        String value = this.valueTransactionTF.getText();
        String description = this.descTransactionTF.getText();
        Type type = this.typeTransactionCB.getValue();
        LocalDate date = this.dateTransactionDP.getValue();
        Double val = 0.0;
        try {
            val = Double.parseDouble(value);
            Transaction trans = new Transaction(val, description, type, date);
            this.transactionList.addTransaction(trans);
            valueTransactionTF.clear();
            descTransactionTF.clear();
            typeTransactionCB.setValue(null);
            dateTransactionDP.setValue(null);
            labelAlert.setText("Transacción registrada");
        } catch (NumberFormatException var6) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al la transacción");
            alert.setContentText("El valor debe ser un número válido.");
            alert.showAndWait();
        }

    }



    @FXML
    public void onClose(ActionEvent event) {
        FXMLLoader loader = HelloApplication.renderView("hello-view.fxml");
        HelloController addEmployeeController = (HelloController) loader.getController();
        addEmployeeController.initialize(transactionList);
        Stage stage = (Stage)this.descTransactionTF.getScene().getWindow();
        stage.close();
    }
}
