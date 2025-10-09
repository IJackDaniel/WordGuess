package com.IJackDaniel.WordGuess;

import com.IJackDaniel.WordGuess.Exceptions.DigitInWordException;
import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printInfo(WordGuessGame game) {
        List<String> guesses = game.getGuesses();
        List<int[]> resultsGuesses = game.getResultsGuesses();
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
        printKeyboard(game);
    }

    public static void printKeyboard(WordGuessGame game) {
        Map<Character, Integer> keyboard = game.getAlphabet();
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
                clearConsole();
                printInfo(game);
                System.out.print("\nВведите слово из 5 букв: ");
                try {
                    tryInput = bufferedReader.readLine();
                } catch (Exception exception) {
                    System.out.println("Ошибка ввода");
                    continue;
                }

                try {
                    result = game.inputWord(tryInput);
                } catch (InvalidWordException | LengthArrayException | DigitInWordException exception) {
                    System.out.println("\n" + exception.getMessage());
                    continue;
                }
                int sum = 0;
                for (int digit : result) {
                    //                    System.out.print(digit + " ");
                    sum += digit;
                }

                if (sum == 5 * 2) {
                    win = true;
                }
            }
            System.out.println("\nВы выиграли! Загаданное слово: " + game.getGuessWord());
        } finally {
            AnsiConsole.systemUninstall();
        }
    }
}
