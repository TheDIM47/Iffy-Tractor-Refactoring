package com.juliasoft.game.intf;

public interface Unit<U extends Unit<U>> {
    default U perform(Action<U> action) {
        return action.apply((U) this);
    }
}
