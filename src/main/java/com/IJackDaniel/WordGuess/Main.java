package com.IJackDaniel.WordGuess;

import com.IJackDaniel.WordGuess.Exceptions.WordException;
import com.IJackDaniel.WordGuess.Model.WordGuessGame;
import com.IJackDaniel.WordGuess.View.WordGuessViewCmd;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        try {
            WordGuessGame game = new WordGuessGame();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in, StandardCharsets.UTF_8));

            String tryInput;
            boolean win = false;
            while (!win) {
                WordGuessViewCmd.clearConsole();
                WordGuessViewCmd.printInfo(game.getGameData());
                game.resetLastException();
                try {
                    tryInput = bufferedReader.readLine();
                } catch (Exception exception) {
                    game.setLastException(exception);
                    continue;
                }

                try {
                    game.inputWord(tryInput);
                    if (game.isWin()) win = true;
                } catch (WordException exception) {
                    game.setLastException(exception);
                }
            }
            WordGuessViewCmd.printWin(game.getGameData());
        } finally {
            AnsiConsole.systemUninstall();
        }
    }
}
