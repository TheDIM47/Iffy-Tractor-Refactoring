package com.juliasoft.game.actions;

import com.juliasoft.game.intf.Action;
import com.juliasoft.game.intf.Unit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * "Макро-команда" - по-факту список команд
 */
public class MacroAction<U extends Unit<U>> implements Action<U> {
    // as macro
    public static final MacroAction DOUBLE_STEP = new MacroAction(MoveAction.STEP, MoveAction.STEP);
    // 2 шага вперед, поворот, 1 шаг вперед
    public static final MacroAction LONG_CW = new MacroAction(DOUBLE_STEP, TurnAction.CLOCKWISE, MoveAction.STEP);
    public static MacroAction LONG_CCW = new MacroAction(DOUBLE_STEP, TurnAction.COUNTERCLOCKWISE, MoveAction.STEP);
    // 1 шаг вперед, поворот, 2 шага вперед
    public static MacroAction SHORT_CW = new MacroAction(MoveAction.STEP, TurnAction.CLOCKWISE, DOUBLE_STEP);
    public static MacroAction SHORT_CCW = new MacroAction(MoveAction.STEP, TurnAction.COUNTERCLOCKWISE, DOUBLE_STEP);


    public MacroAction(List<Action<U>> actions) {
        this.actions = actions;
    }

    public MacroAction(Action<U>... arr) {
        this(Arrays.asList(arr));
    }

    public Collection<Action<U>> getActions() {
        return actions;
    }

    @Override
    public U apply(U u) {
        for (Action<U> x : actions) {
            u = x.apply(u);
        }
        return u;
    }

    private final List<Action<U>> actions;
}
