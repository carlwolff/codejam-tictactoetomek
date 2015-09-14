package com.tictac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private Symbol[][] board;

    public Board(int size) {
        board = new Symbol[size][size];

        for (int row = 0; row < size; row++) {
            Arrays.fill(board[row], Symbol.DOT);
        }
    }

    public int getSize() {
        return board.length;
    }

    public void setRow(int row, List<Symbol> rowData) {
        for (int col = 0; col < rowData.size(); col++) {
            board[row][col] = rowData.get(col);
        }
    }

    public void setRow(int row, String rowData) {
        setRow(row, Symbol.fromString(rowData));
    }

    List<Symbol> getRow(int row) {
        return Arrays.asList(board[row]);
    }

    List<Symbol> getCol(int columnNumber) {
        ArrayList<Symbol> column = new ArrayList<Symbol>();
        for (int row = 0; row < board.length; row++) {
            column.add(board[row][columnNumber]);
        }
        return column;
    }

    List<Symbol> getDiagonal(boolean startTopLeft) {
        List<Symbol> diagonal = new ArrayList<Symbol>();
        for (int row = 0; row < board.length; row++) {
            int col = startTopLeft ? row : 3 - row;
            diagonal.add(board[row][col]);
        }
        return diagonal;
    }

    public boolean hasEmptySpots() {
        int countDot = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == Symbol.DOT) {
                    countDot++;
                }
            }
        }

        return countDot == 0;
    }
}
