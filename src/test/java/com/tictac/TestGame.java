package com.tictac;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestGame {

    private String symbolsToString(List<Symbol> symbols) {
        return symbols.stream()
            .map(symbol -> String.valueOf(symbol.getCharacter()))
            .reduce("", String::concat);
    }

    @Test
    public void TestXWon() {
        Board board = new Board(4);

        board.setRow(0, "XXXT");
        board.setRow(1, "....");
        board.setRow(2, "OO..");
        board.setRow(3, "....");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.X_WON));
    }

    @Test
    public void TestDraw() {
        Board board = new Board(4);

        board.setRow(0, "XOXT");
        board.setRow(1, "XXOO");
        board.setRow(2, "OXOX");
        board.setRow(3, "XXOO");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.DRAW));
    }

    @Test
    public void TestGameNotComplete() {
        Board board = new Board(4);

        board.setRow(0, "XOX.");
        board.setRow(1, "OX..");
        board.setRow(2, "....");
        board.setRow(3, "....");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.NOT_DONE));
    }

    @Test
    public void TestOWonOnColumn() {
        Board board = new Board(4);

        board.setRow(0, "OOXX");
        board.setRow(1, "OXXX");
        board.setRow(2, "OX.T");
        board.setRow(3, "O..O");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.O_WON));
    }

    @Test
    public void TestOWonOnDiagonalTopRight() {
        Board board = new Board(4);

        board.setRow(0, "XXXO");
        board.setRow(1, "..O.");
        board.setRow(2, ".O..");
        board.setRow(3, "T...");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.O_WON));
    }

    @Test
    public void TestOWonOnDiagonalTopLeft() {
        Board board = new Board(4);

        board.setRow(0, "OXXX");
        board.setRow(1, "XO..");
        board.setRow(2, "..O.");
        board.setRow(3, "...O");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.O_WON));
    }

    @Test
    public void TestOWonOnDiagonalTopLeftWithT() {
        Board board = new Board(4);

        board.setRow(0, "OXXX");
        board.setRow(1, "XO..");
        board.setRow(2, "..T.");
        board.setRow(3, "...O");

        TicTac tictac = new TicTac(board);
        assertThat(tictac.checkWinner(), is(TicTac.Outcome.O_WON));
    }
}
