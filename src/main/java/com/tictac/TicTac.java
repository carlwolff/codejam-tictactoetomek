package com.tictac;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TicTac {
    public enum Outcome { X_WON, O_WON, DRAW, NOT_DONE };
    private Board board;

    public TicTac() {
        this.board = new Board(4);
    }

    public TicTac(Board board) {
        this.board = board;
    }

    private void setupBoard(List<String> boardInput) {
        int row = 0;
        while(row < boardInput.size()) {
            board.setRow(row, boardInput.get(row));
            row++;
        }
    }

    public Outcome checkWinner() {
        Outcome winner = Outcome.NOT_DONE;

        // Row
        winner = checkRowWinner();
        if (winner != Outcome.NOT_DONE) {
            return winner;
        }

        // Column
        winner = checkColumnWinner();
        if (winner != Outcome.NOT_DONE) {
            return winner;
        }

        // Diagonal
        winner = checkDiagonalWinner();
        if (winner != Outcome.NOT_DONE) {
            return winner;
        }

        // Draw?
        if (board.hasEmptySpots()) {
            return Outcome.DRAW;
        }

        return Outcome.NOT_DONE;
    }

    private Outcome checkRowWinner() {
        Outcome winner = Outcome.NOT_DONE;
        int currentRow = 0;

        while (winner == Outcome.NOT_DONE && currentRow < board.getSize()) {
            winner = checkList(board.getRow(currentRow));
            currentRow++;
        }

        return winner;
    }

    private Outcome checkColumnWinner() {
        Outcome winner = Outcome.NOT_DONE;
        int currentColumn = 0;

        while (winner == Outcome.NOT_DONE && currentColumn < board.getSize()) {
            winner = checkList(board.getCol(currentColumn));
            currentColumn++;
        }

        return winner;
    }

    private Outcome checkDiagonalWinner() {
        Outcome winner;

        winner = checkList(board.getDiagonal(true));
        if (winner != Outcome.NOT_DONE) {
            return winner;
        }

        winner = checkList(board.getDiagonal(false));
        if (winner != Outcome.NOT_DONE) {
            return winner;
        }

        return winner;
    }

    private Outcome checkList(List<Symbol> list) {
        int countX = 0;
        int countO = 0;

        // No winner if there's a blank spot
        if (list.contains(Symbol.DOT)) {
            return Outcome.NOT_DONE;
        }

        for (Symbol spot : list) {
            if (spot == Symbol.X) {
                countX++;
            } else if (spot == Symbol.O) {
                countO++;
            } else {
                countX++;
                countO++;
            }
        }

        if (countO == list.size()) {
            return Outcome.O_WON;
        }

        if (countX == list.size()) {
            return Outcome.X_WON;
        }

        return Outcome.NOT_DONE;
    }

    public static void main(String[] args) {
        Path path = Paths.get(args[0]);
        int numberOfTests;

        try {
            LineIterator lineIterator = FileUtils.lineIterator(path.toFile(), "UTF-8");
            numberOfTests = Integer.parseInt(lineIterator.nextLine());

            for (int currentTest = 1; currentTest <= numberOfTests; currentTest++) {
                int nextRow = 0;

                List<String> boardInput = new ArrayList<String>();
                while (lineIterator.hasNext() && nextRow < 4) {
                    boardInput.add(lineIterator.nextLine());
                    nextRow++;
                }

                TicTac tictac = new TicTac();
                tictac.setupBoard(boardInput);
                String outcome = "Game has not completed";

                switch (tictac.checkWinner()) {
                    case X_WON: outcome = "X won"; break;
                    case O_WON: outcome = "O won"; break;
                    case DRAW:  outcome = "Draw"; break;
                }

                System.out.println(String.format("Case #%d: %s", currentTest, outcome));

                // Skip line separating tests
                String blankLine = lineIterator.nextLine();
                assert (blankLine.equals(System.getProperty("line.separator")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
