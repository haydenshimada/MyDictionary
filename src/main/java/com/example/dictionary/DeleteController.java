package com.example.dictionary;

import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;

public class DeleteController {
    private final SQL sql = new SQL();

    public DeleteController() throws SQLException {
    }

    @FXML
    public void saveDelete() throws SQLException {
        sql.deleteWord(HelloController.word);

        cancelButtonClicked();
    }

    @FXML
    private Button deleteButton = new Button();

    @FXML
    public void cancelButtonClicked() {
        Stage stage = (Stage) deleteButton.getScene().getWindow();

        stage.close();
    }
}
