package com.tictac;

import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestBoard {

    Board board;

    @Before
    public void SetupBoard() {
        board = new Board(4);
        board.setRow(0, "XXXX");
        board.setRow(1, "X..X");
        board.setRow(2, "X.TO");
        board.setRow(3, "O..X");
    }

    @Test
    public void TestBoardInit() {

        // getRow and getCol public due to these tests
        //assertThat(board.getRow(0).stream().map(n->n.toString()), is(Arrays.asList(Symbol.X, Symbol.X,Symbol.X,Symbol.X)));
    }

    @Test
    public void TestRows() {
        assertThat(symbolsToString(board.getRow(0)), is("XXXX"));
        assertThat(symbolsToString(board.getRow(2)), is("X.TO"));
    }

    @Test
    public void TestCols() {
        assertThat(symbolsToString(board.getCol(0)), is("XXXO"));
        assertThat(symbolsToString(board.getCol(1)), is("X..."));
    }

    @Test
    public void TestDiagonals() {
        assertThat(symbolsToString(board.getDiagonal(true)), is("X.TX"));
        assertThat(symbolsToString(board.getDiagonal(false)), is("X..O"));
    }

    private String symbolsToString(List<Symbol> symbols) {
        return symbols.stream()
            .map(symbol -> String.valueOf(symbol.getCharacter()))
            .reduce("", String::concat);
    }
}
