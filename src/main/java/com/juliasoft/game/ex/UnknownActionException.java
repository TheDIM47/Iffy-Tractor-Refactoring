package com.juliasoft.game.ex;

import com.juliasoft.game.intf.Action;

/**
 * Неизвестное юниту действие
 */
public class UnknownActionException extends RuntimeException {
    public UnknownActionException(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    private final Action action;
}
