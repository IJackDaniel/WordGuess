package com.IJackDaniel.WordGuess;

public class WordGuessGame {
    private String guessWord;

    public WordGuessGame() {
        // Get random word from list
        this.guessWord = "купол";
    }

    public int[] inputWord(String inputWord) {
        // Array of input results:
        // 0 - There is no letter in the word
        // 1 - The letters are in the word, but not in their place
        // 2 - The letter is in the word and in its place
        int[] result = new int[] {0, 0, 0, 0, 0};

        char[] charArrayGuessWord = this.guessWord.toCharArray();
        char[] charArrayInputWord = inputWord.toCharArray();

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
}
