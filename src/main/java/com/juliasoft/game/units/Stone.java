package com.juliasoft.game.units;

import com.juliasoft.game.actions.MoveAction;
import com.juliasoft.game.boards.Position;
import com.juliasoft.game.ex.UnknownActionException;
import com.juliasoft.game.intf.UnitWithPosition;

/**
 * Камень.
 * Состояние: только позиция.
 * Команды не поддерживаются.
 */
public class Stone implements UnitWithPosition<Stone> {
    public Stone(Position position) {
        this.position = position;
    }

    /**
     * Любая команда игнорируется. Состояние объекта неизменно.
     * @param action команда
     * @return самого себя
     * @throws UnknownActionException
     */
    @Override
    public Stone move(MoveAction action) {
        return this;
    }

    @Override
    public Position getPosition() {
        return position;
    }


    private final Position position;
}
