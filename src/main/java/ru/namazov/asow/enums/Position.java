package ru.namazov.asow.enums;

public enum Position {

    HEAD(1),
    TAIL(2);

    private final int position;

    Position(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
