package com.example.dictionary;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoadingScreen implements Initializable {
    private BorderPane borderPane;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        splash();
    }

    private void splash() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1200);
                } catch (Exception e) {
                    System.out.println(e);
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            borderPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Window/mainWindow.fxml")));
                            Stage stage = new Stage();
                            Scene scene = new Scene(borderPane);

                            stage.setTitle("Từ điển Anh-Việt");
                            stage.getIcons().add(new Image("file:src/main/resources/com/example/dictionary/Image/icon.jpg"));
                            stage.setScene(scene);

                            stage.show();
                            pane.getScene().getWindow().hide();

                        } catch (IOException e) {
                            Logger.getLogger(LoadingScreen.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                });
            }
        }.start();
    }
}
