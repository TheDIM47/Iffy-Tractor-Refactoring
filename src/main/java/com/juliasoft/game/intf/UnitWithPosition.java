package com.juliasoft.game.intf;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.boards.Position;

/**
 * Интерфейс юнита со свойством позиции
 */
public interface UnitWithPosition<U extends UnitWithPosition<U>> extends Unit<U> {
    Position getPosition();

    U move(MoveAction action);
}
