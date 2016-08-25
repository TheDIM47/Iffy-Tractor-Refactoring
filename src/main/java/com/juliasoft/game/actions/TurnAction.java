package com.juliasoft.game.actions;

import com.juliasoft.game.intf.Action;
import com.juliasoft.game.intf.UnitWithOrientation;

/**
 * Действие - поворот
 *
 * clockWise - направление поворота
 */
public class TurnAction<U extends UnitWithOrientation<U>> implements Action<U> {
    public static TurnAction CLOCKWISE = new TurnAction(true);
    public static TurnAction COUNTERCLOCKWISE = new TurnAction(false);

    private TurnAction(boolean clockWise) {
        this.clockWise = clockWise;
    }

    @Override
    public U apply(U u) {
        return u.turn(this);
    }

    public final boolean clockWise;
}
