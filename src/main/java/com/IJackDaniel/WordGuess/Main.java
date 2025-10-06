package com.IJackDaniel.WordGuess;

import com.IJackDaniel.WordGuess.Exceptions.InvalidWordException;
import com.IJackDaniel.WordGuess.Exceptions.LengthArrayException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) throws LengthArrayException, InvalidWordException {
        WordGuessGame game = new WordGuessGame();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in, StandardCharsets.UTF_8));

        String tryInput;
        int[] result;
        boolean win = false;

        while (!win) {
            System.out.print("\nВведите слово из 5 букв: ");
            try {
                tryInput = bufferedReader.readLine();
            } catch (Exception exception) {
                System.out.println("Ошибка ввода");
                continue;
            }

            try {
                result = game.inputWord(tryInput);
            } catch (InvalidWordException | LengthArrayException exception) {
                System.out.println("\n" + exception.getMessage());
                continue;
            }
            int sum = 0;
            for (int digit : result) {
                System.out.print(digit + " ");
                sum += digit;
            }

            if (sum == 5 * 2) {
                win = true;
            }
        }
        System.out.println("\nВы выиграли!");
    }
}
