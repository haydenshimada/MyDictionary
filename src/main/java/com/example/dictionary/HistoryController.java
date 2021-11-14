package com.example.dictionary;

import com.example.dictionary.API.HistoryAPI;
import com.example.dictionary.API.TextToSpeech;
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

public class HistoryController implements Initializable {
    private final SQL sql = new SQL();
    private final HistoryAPI history = new HistoryAPI();

    @FXML
    private Button delete1WordButton = new Button();

    @FXML
    private Button speakButton = new Button();

    @FXML
    private ListView<String> historyList = new ListView<>();

    @FXML
    private TextArea meaning = new TextArea();

    private List<String> wordList = new ArrayList<>();

    public HistoryController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        history.getHistoryData();
        wordList = history.getHistorySet();
        historyList.getItems().addAll(wordList);

        delete1WordButton.setVisible(false);
        speakButton.setVisible(false);
    }

    private String word = "";

    @FXML
    public void getMeaning() throws SQLException {
        meaning.clear();

        word = historyList.getSelectionModel().getSelectedItem();

        if (word != null) {
            meaning.setText(sql.searchWord(word));
            delete1WordButton.setVisible(true);
            speakButton.setVisible(true);
        }
    }

    @FXML
    public void delete1Word() {
        HistoryAPI.deleteWord(word);

        speakButton.setVisible(false);
        delete1WordButton.setVisible(false);

        wordList.remove(word);
        meaning.clear();

        historyList.getItems().clear();
        historyList.getItems().addAll(wordList);
    }

    @FXML
    public void clearHistory() {
        historyList.getItems().clear();
        meaning.clear();
        clearTheFile();
    }

    @FXML
    public void speak() {
        TextToSpeech.Speak(word);
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
