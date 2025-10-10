package com.IJackDaniel.WordGuess.View;

import com.IJackDaniel.WordGuess.Model.GameData;
import com.IJackDaniel.WordGuess.Model.WordGuessGame;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.List;
import java.util.Map;

public class WordGuessViewCmd {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printInfo(GameData gameData) {
        List<String> guesses = gameData.getGuesses();
        List<int[]> resultsGuesses = gameData.getResultsGuesses();
        if (!guesses.isEmpty()) {
            for (int i = 0; i < guesses.size(); i++) {
                String guessWord = guesses.get(i);
                int[] result = resultsGuesses.get(i);
                for (int j = 0; j < guessWord.length(); j++) {
                    if (result[j] == 2) {
                        AnsiConsole.out().print(Ansi.ansi().bgGreen().fgBlack().a(guessWord.charAt(j)).reset());
                    } else if (result[j] == 1) {
                        AnsiConsole.out().print(Ansi.ansi().bgYellow().fgBlack().a(guessWord.charAt(j)).reset());
                    } else {
                        AnsiConsole.out().print(Ansi.ansi().bgRed().fgBlack().a(guessWord.charAt(j)).reset());
                    }
                }

                System.out.print("\n");
            }
        }
        System.out.print("\n");
        printKeyboard(gameData);
        System.out.print("\nВведите слово из 5 букв: ");
    }

    public static void printKeyboard(GameData gameData) {
        Map<Character, Integer> keyboard = gameData.getAlphabet();
        for (Character key : keyboard.keySet()) {
            if (keyboard.get(key) == 2) {
                AnsiConsole.out().print(Ansi.ansi().bgGreen().fgBlack().a(key).reset());
            } else if (keyboard.get(key) == 1) {
                AnsiConsole.out().print(Ansi.ansi().bgYellow().fgBlack().a(key).reset());
            } else if (keyboard.get(key) == 0){
                AnsiConsole.out().print(Ansi.ansi().bgRed().fgBlack().a(key).reset());
            } else {
                AnsiConsole.out().print(Ansi.ansi().bgRgb(255, 255, 255).fgBlack().a(key).reset());
            }
            if (key.equals('ъ') || key.equals('э')) System.out.print("\n");

        }
    }

    public static void printWin(GameData gameData) {
        System.out.println("\nВы выиграли! Загаданное слово: " + gameData.getGuessWord());
    }

    public static void printError(Exception exception) {
        System.out.println("Ошибка!");
        System.out.println(exception.getMessage());
    }
}
