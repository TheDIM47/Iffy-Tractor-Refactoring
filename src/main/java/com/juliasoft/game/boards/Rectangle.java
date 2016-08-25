package com.juliasoft.game.boards;

/**
 * As is
 */
public class Rectangle {
    public Rectangle(Position bottomLeft, Position topRight) {
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public final Position bottomLeft;
    public final Position topRight;
}
