package com.example.dictionary;

import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddController {
    private final SQL sql = new SQL();

    public AddController() throws SQLException {
    }

    @FXML
    private TextArea insertMeaning;

    @FXML
    private TextField insertWord;

    @FXML
    public void saveAdd() throws SQLException {
        sql.insertNewWord(insertWord.getText(), insertMeaning.getText());
        cancelButtonClicked();
    }

    @FXML
    private Button addButton = new Button();

    @FXML
    public void cancelButtonClicked() {
        Stage stage = (Stage) addButton.getScene().getWindow();

        stage.close();
    }
}
