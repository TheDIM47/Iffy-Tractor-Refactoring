package com.juliasoft.game.actions;

import com.juliasoft.game.intf.Action;
import com.juliasoft.game.intf.UnitWithPosition;

/**
 * Действие - перемещение
 *
 * step - количество "шагов" ("переместить на" step клеток доски)
 */
public class MoveAction<U extends UnitWithPosition<U>> implements Action<U> {
    public static final MoveAction STAY = new MoveAction(0);
    public static final MoveAction STEP = new MoveAction();
    public static final MoveAction STEP2 = new MoveAction(2);

    public MoveAction(int step) {
        if (step < 0) {
            throw new IllegalArgumentException("Шаг должен бать положительным или нулевым числом");
        }
        this.step = step;
    }

    @Override
    public U apply(U u) {
        return u.move(this);
    }

    public MoveAction() {
        this(1);
    }

    public final int step;
}
