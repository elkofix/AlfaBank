package com.example.demo.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;

public class TransactionList {

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();
}
