package com.example.dictionary;

import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    private final SQL sql = new SQL();

    public EditController() throws SQLException {
    }

    @FXML
    private TextArea editArea = new TextArea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editArea.setText(HelloController.meaning);
    }

    @FXML
    public void saveEdit() throws SQLException {
        sql.updateWordDetail(HelloController.word, editArea.getText());
        HelloController.meaning = editArea.getText();

        // close editWindow
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button editButton = new Button();

    @FXML
    public void cancelButtonClicked() {
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.close();
    }
}
