package com.IJackDaniel.WordGuess.Model;

import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;
import com.IJackDaniel.WordGuess.Exceptions.OutOfAlphabetException;
import com.IJackDaniel.WordGuess.Exceptions.WordException;

import java.util.*;

public class WordGuessGame {
    private String guessWord;
    private List<String> possibleWords;
    private List<String> userGuesses;
    private List<int[]> resultsGuesses;
    private Map<Character, Integer> gameAlphabet;
    private DictionaryReader dictionaryReader;
    private final String RUSSIAN_ALPHABET = "йцукенгшщзхъфывапролджэячсмитьбю";

    public WordGuessGame() {
        this.dictionaryReader = new DictionaryReader();
        this.possibleWords = dictionaryReader.getWords();
        startGame();
    }

    public void startGame() {
        this.userGuesses = new ArrayList<>();
        this.resultsGuesses = new ArrayList<>();
        gameAlphabet = new LinkedHashMap<>();

        char[] arrayOfLetters = RUSSIAN_ALPHABET.toCharArray();
        for (char letter : arrayOfLetters) {
            gameAlphabet.put(letter, -1);
        }

        Random random = new Random();
        int randInt = random.nextInt(possibleWords.toArray().length);
        this.guessWord = possibleWords.get(randInt);
    }

    public int[] inputWord(String inputWord) {
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
            if (!gameAlphabet.containsKey(character)) {
                throw new OutOfAlphabetException("Ведённые символы должны быть частью алфавита");
            }
        }

        if (!possibleWords.contains(inputWord)) {
            throw new InvalidWordException("Такого слова не существует");
        }

        for (int i = 0; i < charArrayInputWord.length; i++) {
            char inputChar = charArrayInputWord[i];
            char guessChar = charArrayGuessWord[i];

            if (gameAlphabet.get(inputChar) == -1) gameAlphabet.put(inputChar, 0);

            if (inputChar == guessChar) {
                result[i] = 2;
                gameAlphabet.put(inputChar, 2);
            } else {
                for (int j = 0; j < charArrayGuessWord.length; j++) {
                    char c = charArrayGuessWord[j];
                    if (inputChar == c) {
                        result[i] = 1;
                        if (gameAlphabet.get(inputChar) != 2) gameAlphabet.put(inputChar, 1);
                        charArrayGuessWord[j] = '0';
                        break;
                    }
                }
            }
        }
        userGuesses.add(inputWord);
        resultsGuesses.add(result);

        return result;
    }

    public GameData getGameData() {
        return new GameData(this.guessWord ,this.userGuesses, this.resultsGuesses, this.gameAlphabet);
    }
}
