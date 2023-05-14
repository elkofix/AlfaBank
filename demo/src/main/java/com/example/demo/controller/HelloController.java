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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.function.Predicate;

public class HelloController {


    @FXML
    private TableView<Transaction> tableView;

    @FXML
    private VBox vb;

    @FXML
    private Label balanceLb;
    @FXML
    private TableColumn<Transaction, Double> valueColumn;
    @FXML
    private TableColumn<Transaction, String> descColum;

    @FXML
    private TableColumn<Transaction, Type> typeColum;

    @FXML
    private ComboBox<String> combo_box;
    @FXML
    private TableColumn<Transaction, LocalDate> dateColum;
    @FXML
    private Button addButton;
    @FXML
    private Button incomeButton;
    @FXML
    private Button outcomeButton;

    @FXML
    private Button completeButton;

    public TransactionList getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(TransactionList transactionList) {
        this.transactionList = transactionList;
    }


    private TransactionList transactionList;

    public void initialize(TransactionList transactionList){
        this.transactionList = transactionList;
        this.valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.descColum.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.typeColum.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.dateColum.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tableView.setItems(transactionList.getTransactions());
        this.balanceLb.setText("Balance: "+calculateBalance());
        if(calculateBalance()<0){
            this.balanceLb.setStyle("-fx-text-fill: RED");
        }else if(calculateBalance()>0){
            this.balanceLb.setStyle("-fx-text-fill: GREEN");
        }

    }

    public int calculateBalance(){
        int income = 0;
        int outcome = 0;
        for (Transaction tr: transactionList.getTransactions()) {
            if(tr.getType()==Type.INCOME){
                income+=tr.getValue();
            }else{
                outcome+=tr.getValue();
            }
        }
        return income-outcome;
    }

    public void onAddTransaction(ActionEvent event){
        FXMLLoader loader = HelloApplication.renderView("add-transaction-view.fxml");
        AddTransactionController addEmployeeController = (AddTransactionController) loader.getController();
        addEmployeeController.setTransactionList(this.transactionList);
        Stage scena = (Stage)balanceLb.getScene().getWindow();
        scena.close();
    }

    public void onShowIncome(ActionEvent actionEvent) {
        Predicate<Transaction> nameContainsA = transaction -> transaction.getType()==Type.INCOME;
        ObservableList<Transaction> filteredPersons = transactionList.getTransactions().filtered(nameContainsA);
        tableView.setItems(filteredPersons);
    }

    public void onShowOutcome(ActionEvent actionEvent) {
        Predicate<Transaction> nameContainsA = transaction -> transaction.getType()==Type.OUTCOME;
        ObservableList<Transaction> filteredPersons = transactionList.getTransactions().filtered(nameContainsA);
        tableView.setItems(filteredPersons);
    }

    public void onShowCombined(ActionEvent actionEvent) {
        initialize(transactionList);
    }
}