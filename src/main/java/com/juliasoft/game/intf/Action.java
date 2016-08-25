package com.juliasoft.game.intf;

/**
 * Действие. Маркер-интерфейс действий юнитов
 */
public interface Action<U extends Unit<U>> {
    U apply(U u);
}
