package com.IJackDaniel.WordGuess;

import com.IJackDaniel.WordGuess.Exceptions.DigitInWordException;
import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;
import com.IJackDaniel.WordGuess.Model.WordGuessGame;
import com.IJackDaniel.WordGuess.View.WordGuessViewCmd;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws LengthArrayException, InvalidWordException {
        AnsiConsole.systemInstall();
        try {
            WordGuessGame game = new WordGuessGame();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in, StandardCharsets.UTF_8));

            String tryInput;
            int[] result;
            boolean win = false;

            while (!win) {
                WordGuessViewCmd.clearConsole();
                WordGuessViewCmd.printInfo(game.getGameData());
                try {
                    tryInput = bufferedReader.readLine();
                } catch (Exception exception) {
                    WordGuessViewCmd.printError(exception);
                    continue;
                }

                try {
                    result = game.inputWord(tryInput);
                } catch (InvalidWordException | LengthArrayException | DigitInWordException exception) {
                    WordGuessViewCmd.printError(exception);
                    continue;
                }

                if (checkWin(result)) win = true;
            }
            WordGuessViewCmd.printWin(game.getGameData());
        } finally {
            AnsiConsole.systemUninstall();
        }
    }

    private static boolean checkWin(int[] result) {
        int sum = 0;
        for (int digit : result) {
            sum += digit;
        }

        return sum == (5 * 2);
    }
}
