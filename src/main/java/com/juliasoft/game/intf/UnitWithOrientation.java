package com.juliasoft.game.intf;

import com.juliasoft.game.actions.TurnAction;

/**
 * Интерфейс юнита со свойством направлением
 */
public interface UnitWithOrientation<U extends UnitWithOrientation<U>> extends Unit<U> {
    Orientation getOrientation();

    U turn(TurnAction action);

    default Orientation turn(boolean clockWise) {
        return clockWise ? getOrientation().clockWise() : getOrientation().counterClockWise();
    }
}
