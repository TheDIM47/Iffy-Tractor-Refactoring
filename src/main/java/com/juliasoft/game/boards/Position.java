package com.juliasoft.game.boards;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.ex.UnknownOrientationException;
import com.juliasoft.game.intf.Orientation;

/**
 * 2D - Позиция (координаты на игровой доске)
 */
public class Position /*implements Coordinate*/ {
    public static final Position ZeroPosition = new Position(0,0);

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position step(Orientation direction, MoveAction move) {
        switch (direction) {
            case NORTH:
                return stepNorth(move.step);
            case EAST:
                return stepEast(move.step);
            case SOUTH:
                return stepSouth(move.step);
            case WEST:
                return stepWest(move.step);
            default:
                throw new UnknownOrientationException(direction);
        }
    }

    private Position stepNorth(int step) {
        return new Position(x, y + step);
    }

    private Position stepEast(int step) {
        return new Position(x + step, y);
    }

    private Position stepSouth(int step) {
        return new Position(x, y - step);
    }

    private Position stepWest(int step) {
        return new Position(x - step, y);
    }

     /**
     * Проверка на вхождение в регион
     *
     * @param p проверяемая позиция
     * @param bottomLeft нижний левый угол (включительно)
     * @param topRight верхний правый угол (исключительно)
     * @return true если объект внутри прямоугольника
     */
    public static boolean inRect(Position p, Rectangle rect) {
        final int bx = rect.bottomLeft.x;
        final int by = rect.bottomLeft.y;
        final int tx = rect.topRight.x;
        final int ty = rect.topRight.y;
        return (p.x >= bx && p.y >= by && p.x < tx && p.y < ty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public final int x;
    public final int y;
}
