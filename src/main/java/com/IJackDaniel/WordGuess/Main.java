package com.IJackDaniel.WordGuess;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordGuessGame game = new WordGuessGame();
        Scanner scanner = new Scanner(System.in);

        String tryInput;
        int[] result;
        boolean win = false;
        while (!win) {
            System.out.print("\nВведите слово: ");
            tryInput = scanner.nextLine();
            result = game.inputWord(tryInput);
            int sum = 0;
            for (int digit : result) {
                System.out.print(digit + " ");
                sum += digit;
            }

            if (sum == 5 * 2) {
                win = true;
            }
        }
        System.out.println("\nВы выиграли! Загаданное слово: купол");
    }
}
