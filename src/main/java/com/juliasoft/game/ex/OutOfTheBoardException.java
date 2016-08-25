package com.juliasoft.game.ex;

import com.juliasoft.game.boards.Position;
import com.juliasoft.game.intf.UnitWithPosition;

/**
 * Позиция юнита находится за пределами игрового поля
 */
public class OutOfTheBoardException extends RuntimeException {
    public OutOfTheBoardException(UnitWithPosition unit) {
        this.unit = unit;
    }

    public UnitWithPosition getUnit() {
        return unit;
    }

    public Position getPosition() {
        return unit.getPosition();
    }

    private final UnitWithPosition unit;
}
