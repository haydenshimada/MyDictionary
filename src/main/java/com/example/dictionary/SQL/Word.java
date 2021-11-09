package com.example.dictionary.SQL;

public class Word {
    private String word;
    private String wordDetail;

    public Word() {

    }

    public Word(String word, String wordDetail) {
        this.word = word;
        this.wordDetail = wordDetail;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordDetail() {
        return wordDetail;
    }

    public void setWordDetail(String wordDetail) {
        this.wordDetail = wordDetail;
    }

    public String toString() {
        return word + "\n" + wordDetail;
    }
}
