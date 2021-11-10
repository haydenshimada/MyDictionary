package com.example.dictionary;

import com.example.dictionary.API.TextToSpeech;
import com.example.dictionary.SQL.SQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.dictionary.API.GoogleAPI.translateFromEnToVi;

public class HelloController implements Initializable {
    private Stage window;
    private Scene scene;

    private final SQL sql = new SQL();

    @FXML
    private ListView<String> listView = new ListView<>();

    private List<String> wordList = new ArrayList<>();

    @FXML
    private TextField inputWord = new TextField();

    public HelloController() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteButton.setVisible(false);
        speakButton.setVisible(false);
        editButton.setVisible(false);
    }

    @FXML
    public void getWordList() throws SQLException {
        wordList.clear();
        listView.getItems().clear();

        String input = inputWord.getText();
        wordList = sql.pushToSuggestList(input);
        listView.getItems().addAll(wordList);
    }

    @FXML
    public void addButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/addWindow.fxml"));
        scene = new Scene(fxmlLoader.load());

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Thêm từ mới");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
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
    private Button deleteButton = new Button();

    @FXML
    public void deleteButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/deleteWindow.fxml"));
        scene = new Scene(fxmlLoader.load());

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Xóa từ");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    public void saveDelete() throws SQLException {
        sql.deleteWord(word);

        inputWord.clear();
        meaningArea.clear();

        cancelButtonClicked();
    }

    @FXML
    private Button editButton = new Button();

    @FXML
    public void editButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/editWindow.fxml"));
        scene = new Scene(fxmlLoader.load());

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Sửa từ");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }

    protected static String word = "";
    protected static String meaning = "";

    @FXML
    private Button apiButton;

    @FXML
    public void APIButtonClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/API.fxml"));

        window = (Stage) apiButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    private Button backToMainButton;

    @FXML
    private void getBackToMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Window/mainWindow.fxml"));

        window = (Stage) backToMainButton.getScene().getWindow();
        window.setScene(new Scene(fxmlLoader.load()));
    }

    @FXML
    private Button cancelButton = new Button();

    @FXML
    public void cancelButtonClicked() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button speakButton = new Button();

    @FXML
    public void speak() {
        //String word = listView.getSelectionModel().getSelectedItem();
        TextToSpeech.Speak(word);
    }

    @FXML
    private TextArea meaningArea = new TextArea();

    @FXML
    public void getMeaning() throws SQLException {
        meaningArea.clear();

        word = listView.getSelectionModel().getSelectedItem();

        if (word != null) {
            meaningArea.setText(sql.searchWord(word));
            meaning = meaningArea.getText();

            deleteButton.setVisible(true);
            speakButton.setVisible(true);
            editButton.setVisible(true);
        }
    }

    @FXML
    public void searchButtonClicked() throws SQLException {
        meaningArea.clear();

        if (!wordList.isEmpty()) {
            word = wordList.get(0);

            if (word != null) {
                meaningArea.setText(sql.searchWord(word));

                deleteButton.setVisible(true);
                speakButton.setVisible(true);
                editButton.setVisible(true);
            }
        }
    }

    @FXML
    private TextArea apiInput = new TextArea();
    @FXML
    private TextArea apiOutput = new TextArea();

    @FXML
    public void EnVnButtonClick() throws IOException {
        apiOutput.clear();
        apiOutput.appendText(translateFromEnToVi(apiInput.getText()));
    }

    @FXML
    public void VnEnButtonClick() throws IOException {
        apiOutput.clear();
        apiOutput.appendText(translateFromEnToVi(apiInput.getText()));
    }
}