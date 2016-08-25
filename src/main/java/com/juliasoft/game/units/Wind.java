package com.juliasoft.game.units;

import com.juliasoft.game.actions.TurnAction;
import com.juliasoft.game.intf.Orientation;
import com.juliasoft.game.intf.UnitWithOrientation;

/**
 * Ветер.
 * Состояние: только направление.
 * Команды: поворот
 */
public class Wind implements UnitWithOrientation<Wind> {
    public Wind(Orientation orientation) {
        this.orientation = orientation;
    }

    public Wind() {
        this.orientation = Orientation.NORTH;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Wind turn(TurnAction action) {
        return new Wind(turn(action.clockWise));
    }

    private final Orientation orientation;
}
