package com.tictac;

import java.util.ArrayList;
import java.util.List;

public enum Symbol {
    X('X'), O('O'), T('T'), DOT('.');

    private final Character character;
    Symbol(Character character) {
        this.character = character;
    }

    public char getCharacter() {
        return this.character;
    }

    public String toString() {
        return this.character.toString();
    }

    public static Symbol forChar(Character character) {
        switch (character) {
            case 'X': return X;
            case 'O': return O;
            case 'T': return T;
            default : return DOT;
        }
    }

    public static List<Symbol> fromString(String input) {
        List<Symbol> result = new ArrayList<Symbol>();
        for(Character character : input.toCharArray()) {
            result.add(Symbol.forChar(character));
        }
        return result;
    }
}
