package com.juliasoft.game.units;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.boards.Position;
import com.juliasoft.game.actions.TurnAction;
import com.juliasoft.game.intf.Orientation;
import com.juliasoft.game.intf.UnitWithOrientation;
import com.juliasoft.game.intf.UnitWithPosition;

import static com.juliasoft.game.intf.Orientation.NORTH;

/**
 * Трактор.
 * Состояние: позиция, направление.
 * Команды: движение вперёд, поворот.
 */
public class Tractor implements UnitWithOrientation<Tractor>, UnitWithPosition<Tractor> {
    public Tractor(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Tractor(Position position) {
        this(position, NORTH);
    }

    @Override
    public Tractor turn(TurnAction action) {
        return new Tractor(position, turn(action.clockWise));
    }

    @Override
    public Tractor move(MoveAction action) {
        return new Tractor(position.step(orientation, action), orientation);
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    private final Position position;
    private final Orientation orientation;
}
