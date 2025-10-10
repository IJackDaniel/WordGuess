package com.IJackDaniel.WordGuess.Model;

import java.util.List;
import java.util.Map;

public class GameData {
    private String guessWord;
    private List<String> guesses;
    private List<int[]> resultsGuesses;
    private Map<Character, Integer> alphabet;

    public GameData(String guessWord, List<String> guesses, List<int[]> resultsGuesses, Map<Character, Integer> alphabet) {
        this.guessWord = guessWord;
        this.guesses = guesses;
        this.resultsGuesses = resultsGuesses;
        this.alphabet = alphabet;
    }

    public String getGuessWord() {
        return this.guessWord;
    }

    public List<String> getGuesses() {
        return this.guesses;
    }

    public List<int[]> getResultsGuesses() {
        return this.resultsGuesses;
    }

    public Map<Character, Integer> getAlphabet() {
        return this.alphabet;
    }
}
