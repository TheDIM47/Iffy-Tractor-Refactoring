package com.juliasoft.game.ex;

import com.juliasoft.game.intf.Orientation;

/**
 * Неизвестное направление (ориентация в пространстве)
 */
public class UnknownOrientationException extends RuntimeException {
    public UnknownOrientationException(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    private final Orientation orientation;
}
