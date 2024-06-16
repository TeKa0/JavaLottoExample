/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.kolekcjezadanie3;

import java.util.*;

public class KolekcjeZadanie3 {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        lottoGame.collectUserNumbers();
        lottoGame.collectNumberOfGames();
        lottoGame.playGames();
        lottoGame.displayResults();
    }
}

class LottoGame {
    private static final int NUMBER_RANGE_START = 1;
    private static final int NUMBER_RANGE_END = 49;
    private static final int NUMBERS_COUNT = 6;

    private Set<Integer> userNumbers;
    private int numberOfGames;
    private List<GameResult> gameResults;

    public LottoGame() {
        userNumbers = new HashSet<>();
        gameResults = new ArrayList<>();
    }

    public void collectUserNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wprowadź 6 unikatowych liczb z przedziału <1, 49>:");

        while (userNumbers.size() < NUMBERS_COUNT) {
            try {
                System.out.print("Liczba " + (userNumbers.size() + 1) + ": ");
                int number = Integer.parseInt(scanner.nextLine());

                if (number < NUMBER_RANGE_START || number > NUMBER_RANGE_END) {
                    System.out.println("Liczba musi być z przedziału <1, 49>. Spróbuj ponownie.");
                } else if (userNumbers.contains(number)) {
                    System.out.println("Ta liczba już została wprowadzona. Spróbuj ponownie.");
                } else {
                    userNumbers.add(number);
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa liczba. Spróbuj ponownie.");
            }
        }
    }

    public void collectNumberOfGames() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ile gier chcesz rozegrać? ");

        while (true) {
            try {
                numberOfGames = Integer.parseInt(scanner.nextLine());
                if (numberOfGames > 0) {
                    break;
                } else {
                    System.out.println("Liczba gier musi być większa od 0. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa liczba. Spróbuj ponownie.");
            }
        }
    }

    public void playGames() {
        Random random = new Random();
        for (int i = 0; i < numberOfGames; i++) {
            Set<Integer> drawnNumbers = new HashSet<>();
            while (drawnNumbers.size() < NUMBERS_COUNT) {
                int number = random.nextInt(NUMBER_RANGE_END) + NUMBER_RANGE_START;
                drawnNumbers.add(number);
            }
            int matches = countMatches(drawnNumbers, userNumbers);
            if (matches >= 3) {
                gameResults.add(new GameResult(drawnNumbers, matches));
            }
        }
    }

    public void displayResults() {
        if (gameResults.isEmpty()) {
            System.out.println("Nie było żadnych wygranych.");
        } else {
            System.out.println("Lista wygrywających gier:");
            for (GameResult result : gameResults) {
                System.out.println("Liczby: " + result.getDrawnNumbers() + " - Trafione: " + result.getMatches());
            }
        }
    }

    private int countMatches(Set<Integer> drawnNumbers, Set<Integer> userNumbers) {
        int matches = 0;
        for (int number : drawnNumbers) {
            if (userNumbers.contains(number)) {
                matches++;
            }
        }
        return matches;
    }
}

class GameResult {
    private Set<Integer> drawnNumbers;
    private int matches;

    public GameResult(Set<Integer> drawnNumbers, int matches) {
        this.drawnNumbers = drawnNumbers;
        this.matches = matches;
    }

    public Set<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public int getMatches() {
        return matches;
    }
}