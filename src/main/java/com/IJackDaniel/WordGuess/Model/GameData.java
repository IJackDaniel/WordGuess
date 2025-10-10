package com.IJackDaniel.WordGuess.Model;

import java.util.List;
import java.util.Map;

public class GameData {
    private String guessWord;
    private List<String> userGuesses;
    private List<int[]> resultsGuesses;
    private Map<Character, Integer> gameAlphabet;

    public GameData(String guessWord, List<String> userGuesses, List<int[]> resultsGuesses, Map<Character, Integer> gameAlphabet) {
        this.guessWord = guessWord;
        this.userGuesses = userGuesses;
        this.resultsGuesses = resultsGuesses;
        this.gameAlphabet = gameAlphabet;
    }

    public String getGuessWord() {
        return this.guessWord;
    }

    public List<String> getUserGuesses() {
        return this.userGuesses;
    }

    public List<int[]> getResultsGuesses() {
        return this.resultsGuesses;
    }

    public Map<Character, Integer> getGameAlphabet() {
        return this.gameAlphabet;
    }
}
