package com.juliasoft.game.intf;

import com.juliasoft.game.user.actions.ShotAction;

/**
 * Интерфейс юнита со свойством "патроны"
 */
public interface UnitWithBullets<U extends UnitWithBullets<U>> extends Unit<U> {
    int getBulletCount();

    U shot(ShotAction action);
}
