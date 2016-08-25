package com.juliasoft.game;

import com.juliasoft.game.boards.Position;
import org.junit.Test;
import com.juliasoft.game.actions.MacroAction;
import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.actions.TurnAction;
import com.juliasoft.game.boards.ImmutableBoard;
import com.juliasoft.game.boards.Rectangle;
import com.juliasoft.game.ex.OutOfTheBoardException;
import com.juliasoft.game.intf.Board;
import com.juliasoft.game.units.Stone;
import com.juliasoft.game.units.Tractor;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.juliasoft.game.actions.MacroAction.DOUBLE_STEP;

/**
 * Immutable Board Unit tests
 */
public class ImmutableBoardTest {

    @Test
    public void testEmpty() {
        Board board = new ImmutableBoard(Collections.emptyList(), 5);
        assertThat(board.apply(MoveAction.STEP).getUnits(), equalTo(Collections.emptyList()));
    }

    @Test
    public void testSingle() {
        Board board = new ImmutableBoard(Arrays.asList(new Tractor(Position.ZeroPosition)), 5);
        board = board.apply(MoveAction.STEP);
        assertThat(board.getUnits().isEmpty(), is(false));
        assertThat(board.getUnits().size(), is(1));
        final Tractor t = (Tractor) board.getUnits().iterator().next();
        assertThat(t.getPosition(), is(new Position(0, 1)));
    }

    @Test
    public void testHorseAction() {
        Board board = new ImmutableBoard(Arrays.asList(new Tractor(Position.ZeroPosition)), 5);
        board = board.apply(MacroAction.LONG_CW);
        assertThat(board.getUnits().isEmpty(), is(false));
        assertThat(board.getUnits().size(), is(1));
        final Tractor t = (Tractor) board.getUnits().iterator().next();
        assertThat(t.getPosition(), is(new Position(1, 2)));
    }

    @Test
    public void testSingleStone() {
        Board board = new ImmutableBoard(Arrays.asList(new Stone(Position.ZeroPosition)), 5);
        board = board.apply(MoveAction.STEP).apply(TurnAction.CLOCKWISE);
        assertThat(board.getUnits().isEmpty(), is(false));
        assertThat(board.getUnits().size(), is(1));
        final Stone s = (Stone) board.getUnits().iterator().next();
        assertThat(s.getPosition(), is(new Position(0, 0)));
    }

    @Test
    public void testRegionAction() {
        final Rectangle region = new Rectangle(new Position(0, 0),new Position(1, 1));
        Board board = new ImmutableBoard(Arrays.asList(new Tractor(Position.ZeroPosition)), 5);
        // step action in region
        board = board.apply(region, MoveAction.STEP);
        Tractor t = (Tractor) board.getUnits().iterator().next();
        assertThat(t.getPosition(), is(new Position(0, 1)));
        // step action out of region
        board = board.apply(region, MoveAction.STEP);
        t = (Tractor) board.getUnits().iterator().next();
        assertThat(t.getPosition(), is(new Position(0, 1)));
    }

    @Test
    public void testMacro() {
        Board board = new ImmutableBoard(Arrays.asList(new Tractor(Position.ZeroPosition)), 5);
        board = board.apply(DOUBLE_STEP);
        assertThat(board.getUnits().isEmpty(), is(false));
        assertThat(board.getUnits().size(), is(1));
        final Tractor t = (Tractor) board.getUnits().iterator().next();
        assertThat(t.getPosition(), is(new Position(0, 2)));
    }

    @Test(expected = OutOfTheBoardException.class)
    public void testOutOfBoard() {
        Board board = new ImmutableBoard(Arrays.asList(new Tractor(Position.ZeroPosition)), 5);
        board.apply(TurnAction.COUNTERCLOCKWISE).apply(MoveAction.STEP);
    }

}
