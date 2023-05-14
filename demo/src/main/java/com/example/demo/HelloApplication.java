package com.example.demo;

import com.example.demo.controller.HelloController;
import com.example.demo.model.TransactionList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public void start(Stage stage) throws IOException {
        TransactionList transactionList = new TransactionList();
        FXMLLoader loader = renderView("hello-view.fxml");
        HelloController controller = (HelloController)loader.getController();
        controller.initialize(transactionList);
    }

    public static FXMLLoader renderView(String fxml) {
        FXMLLoader fxmlLoader = null;

        try {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
            Parent parent = (Parent)fxmlLoader.load();
            Scene scene = new Scene(parent, 500, 400);
            Stage stage = new Stage();
            stage.setTitle("Alpha App");
            stage.setScene(scene);
            stage.show();
        } catch (IOException var5) {
            var5.getMessage();
            var5.printStackTrace();
        }

        return fxmlLoader;
    }

    public static void main(String[] args) {
        launch();
    }
}