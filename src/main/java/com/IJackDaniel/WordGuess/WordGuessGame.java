package com.IJackDaniel.WordGuess;

import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class WordGuessGame {
    private String guessWord;
    private ArrayList<String> dataOfWords;

    public WordGuessGame() {
        this.dataOfWords = new ArrayList<>();
        readFromFile();
        startGame();
    }

    public void startGame() {
        Random random = new Random();
        int randInt = random.nextInt(dataOfWords.toArray().length);
        this.guessWord = dataOfWords.get(randInt);
    }

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data\\russianWords.txt"))) {
            String string;
            while ((string=reader.readLine()) != null) {
                this.dataOfWords.add(string);
            }
        } catch (IOException exception) {
            System.out.println("Ошибка! " + exception.getMessage());
        }
    }

    public int[] inputWord(String inputWord) throws LengthArrayException, InvalidWordException {
        // Array of input results:
        // 0 - There is no letter in the word
        // 1 - The letters are in the word, but not in their place
        // 2 - The letter is in the word and in its place
        int[] result = new int[] {0, 0, 0, 0, 0};

        char[] charArrayGuessWord = this.guessWord.toCharArray();
        char[] charArrayInputWord = inputWord.toCharArray();

        if (charArrayInputWord.length != charArrayGuessWord.length) {
            throw new LengthArrayException("В слове должно быть 5 букв");
        }

        if (!dataOfWords.contains(inputWord)) {
            throw new InvalidWordException("Такого слова не существует");
        }

        for (int i = 0; i < charArrayGuessWord.length; i++) {
            if (charArrayInputWord[i] == charArrayGuessWord[i]) {
                result[i] = 2;
            } else {
                for (char c : charArrayGuessWord) {
                    if (charArrayInputWord[i] == c) {
                        result[i] = 1;
                        break;
                    }
                }
            }

        }
        return result;
    }

    public String getGuessWord() {
        return this.guessWord;
    }
}
