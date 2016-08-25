package com.juliasoft.game;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.boards.Position;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import com.juliasoft.game.actions.TurnAction;
import com.juliasoft.game.intf.Orientation;
import com.juliasoft.game.user.actions.ShotAction;
import com.juliasoft.game.user.units.Tank;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * Tank Unit tests
 */
public class TankTest {

    @Test
    public void testShouldMoveForward() {
        final Tank tank = new Tank(Position.ZeroPosition, Orientation.NORTH, 1);
        assertThat(tank.perform(MoveAction.STEP).getPosition(), is(new Position(0, 1)));
        // Original tank stay immutable
        assertThat(tank.getPosition(), CoreMatchers.is(Position.ZeroPosition));
    }

    @Test
    public void testShouldShot() {
        final Tank tank = new Tank(Position.ZeroPosition, Orientation.NORTH, 5);
        assertThat(tank.perform(ShotAction.SHOT).getBulletCount(), is(4));
    }

    @Test
    public void testShouldTurn() {
        final Tank tank = new Tank(Position.ZeroPosition, Orientation.NORTH, 1);
        assertThat(tank.getOrientation(), is(Orientation.NORTH));
        assertThat(tank.perform(TurnAction.CLOCKWISE).getOrientation(), is(Orientation.EAST));
        assertThat(tank.perform(TurnAction.CLOCKWISE).perform(TurnAction.CLOCKWISE).getOrientation(), is(Orientation.SOUTH));
        // Original tank stay immutable
        assertThat(tank.getOrientation(), is(Orientation.NORTH));
        assertThat(tank.perform(TurnAction.COUNTERCLOCKWISE).getOrientation(), is(Orientation.WEST));
        assertThat(tank.perform(TurnAction.COUNTERCLOCKWISE).perform(TurnAction.COUNTERCLOCKWISE).getOrientation(), is(Orientation.SOUTH));
    }

    @Test
    public void testShouldTurnAndMoveInTheRightDirection() {
        Tank tank = new Tank(Position.ZeroPosition, Orientation.NORTH, 1);
        tank = tank.perform(TurnAction.CLOCKWISE).perform(MoveAction.STEP);
        assertThat(tank.getPosition(), is(new Position(1, 0)));
        tank = tank.perform(TurnAction.COUNTERCLOCKWISE).perform(MoveAction.STEP);
        assertThat(tank.getPosition(), is(new Position(1, 1)));
        tank = tank.perform(TurnAction.COUNTERCLOCKWISE).perform(MoveAction.STEP);
        assertThat(tank.getPosition(), is(new Position(0, 1)));
        tank = tank.perform(TurnAction.COUNTERCLOCKWISE).perform(MoveAction.STEP);
        assertThat(tank.getPosition(), CoreMatchers.is(Position.ZeroPosition));
    }
}
