package com.example.dictionary;

import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    private final SQL sql = new SQL();

    public AddController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        existed.setVisible(false);
    }

    @FXML
    private TextArea insertMeaning;

    @FXML
    private TextField insertWord;

    @FXML
    private Label existed;

    @FXML
    public void saveAdd() throws SQLException {
        String word = insertWord.getText();

        if (sql.contain(word)) {
            existed.setVisible(true);
            return;
        }

        sql.insertNewWord(word, insertMeaning.getText());
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
