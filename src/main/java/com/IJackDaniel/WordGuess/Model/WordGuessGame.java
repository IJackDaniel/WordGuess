package com.IJackDaniel.WordGuess.Model;

import com.IJackDaniel.WordGuess.Exceptions.DigitInWordException;
import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;

import java.util.*;

public class WordGuessGame {
    private String guessWord;
    private List<String> dataOfWords;
    private List<String> guesses;
    private List<int[]> resultsGuesses;
    private Map<Character, Integer> alphabet;
    DictionaryReader dictionaryReader;

    public WordGuessGame() {
        this.dataOfWords = new ArrayList<>();
        dictionaryReader = new DictionaryReader();
        dataOfWords = dictionaryReader.getDataOfWords();
        startGame();
    }

    public void startGame() {
        this.guesses = new ArrayList<>();
        this.resultsGuesses = new ArrayList<>();
        alphabet = new LinkedHashMap<>();

        char[] arrayOfLetters = "йцукенгшщзхъфывапролджэячсмитьбю".toCharArray();
        for (char letter : arrayOfLetters) {
            alphabet.put(letter, -1);
        }

        Random random = new Random();
        int randInt = random.nextInt(dataOfWords.toArray().length);
        this.guessWord = dataOfWords.get(randInt);
    }

    public int[] inputWord(String inputWord) throws LengthArrayException, InvalidWordException, DigitInWordException {
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

        for (char character : charArrayInputWord) {
            if (Character.isDigit(character)) {
                throw new DigitInWordException("В введённом слове не должно быть цифр");
            }
        }

        if (!dataOfWords.contains(inputWord)) {
            throw new InvalidWordException("Такого слова не существует");
        }

        for (int i = 0; i < charArrayGuessWord.length; i++) {
            if (alphabet.get(charArrayInputWord[i]) == -1) alphabet.put(charArrayInputWord[i], 0);
            if (charArrayInputWord[i] == charArrayGuessWord[i]) {
                result[i] = 2;
                alphabet.put(charArrayInputWord[i], 2);
            } else {
                for (char c : charArrayGuessWord) {
                    if (charArrayInputWord[i] == c) {
                        result[i] = 1;
                        if (alphabet.get(charArrayInputWord[i]) != 2) alphabet.put(charArrayInputWord[i], 1);
                        break;
                    }
                }
            }
        }
        guesses.add(inputWord);
        resultsGuesses.add(result);

        return result;
    }

    public GameData getGameData() {
        return new GameData(this.guessWord ,this.guesses, this.resultsGuesses, this.alphabet);
    }
}
