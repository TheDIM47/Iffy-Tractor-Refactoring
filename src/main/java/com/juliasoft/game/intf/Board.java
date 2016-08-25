package com.juliasoft.game.intf;

import com.juliasoft.game.boards.Rectangle;

import java.util.Collection;

/**
 * Поле для игры.
 */
public interface Board<U extends Unit<U>, A extends Action<U>> {
    /**
     * Вернет всех юнитов
     * @return
     */
    Collection<U> getUnits();

    /**
     * Выполнит действие (action) над всеми юнитами
     * @param action действие
     * @return Игровое поле в новом состоянии
     */
    Board apply(A action);

    /**
     * Выполнит действие (action) над всеми юнитами в регионе
     * Юниты без позиции ("ветер") данный вызов игнорируют
     * @param rect регион - нижний левый угол региона (включительно), правый верхний угол региона (исключительно)
     * @param action действие
     * @return Игровое поле в новом состоянии
     */
    Board apply(Rectangle rect, A action);
}
