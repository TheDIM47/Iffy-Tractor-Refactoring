package com.juliasoft.game.user.units;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.actions.TurnAction;
import com.juliasoft.game.boards.Position;
import com.juliasoft.game.intf.Orientation;
import com.juliasoft.game.intf.UnitWithBullets;
import com.juliasoft.game.intf.UnitWithOrientation;
import com.juliasoft.game.intf.UnitWithPosition;
import com.juliasoft.game.user.actions.ShotAction;

/**
 * Танк.
 * Состояние: позиция, направление, запас патронов.
 * Команды: движение вперёд, поворот, выстрел.
 * <p/>
 */
public class Tank implements UnitWithOrientation<Tank>, UnitWithPosition<Tank>, UnitWithBullets<Tank> {

    public Tank(Position position, Orientation orientation, int bulletCount) {
        if (bulletCount < 0) {
            throw new IllegalArgumentException("Количество зарядов не может быть меньше нуля");
        }
        this.position = position;
        this.orientation = orientation;
        this.bulletCount = bulletCount;
    }

    @Override
    public Tank move(MoveAction action) {
        return new Tank(position.step(orientation, action), orientation, bulletCount);
    }

    @Override
    public Tank shot(ShotAction action) {
        return new Tank(position, orientation, this.bulletCount - action.bulletCount);
    }

    @Override
    public Tank turn(TurnAction action) {
        return new Tank(position, turn(action.clockWise), bulletCount);
    }

    @Override
    public int getBulletCount() {
        return bulletCount;
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
    private final int bulletCount;
}
