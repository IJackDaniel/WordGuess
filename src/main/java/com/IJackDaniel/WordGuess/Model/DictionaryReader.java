package com.IJackDaniel.WordGuess.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DictionaryReader {
    private final List<String> possibleWords;

    public DictionaryReader() {
        possibleWords = new ArrayList<>();
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\russianWords.txt"))) {
            String string;
            while ((string=reader.readLine()) != null) {
                this.possibleWords.add(string);
            }
        } catch (IOException exception) {
            System.out.println("Ошибка! " + exception.getMessage());
        }
    }

    public List<String> getWords() {
        return possibleWords;
    }
}
