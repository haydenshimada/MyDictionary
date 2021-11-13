package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Window/loadScreen.fxml")));
        Scene scene = new Scene(pane);

        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Window/mainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Từ điển Anh-Việt");
        stage.getIcons().add(new Image("file:src/main/resources/com/example/dictionary/Image/icon.jpg"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
         */
    }

    public static void main(String[] args) {
        launch();
    }
}