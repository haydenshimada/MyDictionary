package com.example.dictionary;

import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.dictionary.API.HistoryAPI.clearTheFile;
import static com.example.dictionary.API.HistoryAPI.getHistorySet;

public class HistoryController implements Initializable {
    private final SQL sql = new SQL();

    @FXML
    private Button delete1WordButton = new Button();

    @FXML
    private ListView<String> historyList = new ListView<>();

    @FXML
    private TextArea meaning = new TextArea();

    private List<String> wordList = new ArrayList<>();

    public HistoryController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordList = getHistorySet();
        historyList.getItems().addAll(wordList);

        delete1WordButton.setVisible(false);
    }

    @FXML
    public void getMeaning() throws SQLException {
        meaning.clear();

        String word = historyList.getSelectionModel().getSelectedItem();

        if (word != null) {
            meaning.setText(sql.searchWord(word));
        }
    }

    @FXML
    public void clearHistory() {
        historyList.getItems().clear();
        meaning.clear();
        clearTheFile();
    }

    @FXML
    private Button backToMainButton;

    @FXML
    private void getBackToMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/mainWindow.fxml"));

        Stage window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }
}
