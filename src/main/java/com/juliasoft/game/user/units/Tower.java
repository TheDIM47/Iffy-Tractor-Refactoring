package com.juliasoft.game.user.units;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.boards.Position;
import com.juliasoft.game.intf.Orientation;
import com.juliasoft.game.intf.UnitWithBullets;
import com.juliasoft.game.intf.UnitWithOrientation;
import com.juliasoft.game.intf.UnitWithPosition;
import com.juliasoft.game.user.actions.ShotAction;
import com.juliasoft.game.actions.TurnAction;

/**
 * Охранная башня.
 * Состояние: позиция, направление, запас патронов.
 * Команды: поворот, выстрел
 */
public class Tower implements UnitWithPosition<Tower>, UnitWithOrientation<Tower>, UnitWithBullets<Tower> {
    public Tower(Position position, Orientation orientation, int bulletCount) {
        if (bulletCount < 0) {
            throw new IllegalArgumentException("Количество зарядов не может быть меньше нуля");
        }
        this.position = position;
        this.orientation = orientation;
        this.bulletCount = bulletCount;
    }

    @Override
    public Tower shot(ShotAction action) {
        return new Tower(position, orientation, this.bulletCount - action.bulletCount);
    }

    @Override
    public Tower turn(TurnAction action) {
        return new Tower(position, turn(action.clockWise), bulletCount);
    }

    @Override
    public Tower move(MoveAction action) {
        return this;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getBulletCount() {
        return bulletCount;
    }

    private final Position position;
    private final Orientation orientation;
    private final int bulletCount;
}
