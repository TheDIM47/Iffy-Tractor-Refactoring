package com.juliasoft.game.user.actions;

import com.juliasoft.game.intf.Action;
import com.juliasoft.game.intf.UnitWithBullets;

/**
 * Действие - Выстрел
 *
 * bulletCount - количество зарядов в выстреле (очередь)
 */
public class ShotAction<U extends UnitWithBullets<U>> implements Action<U> {
    public static final ShotAction SHOT = new ShotAction(1);

    public ShotAction(int bulletCount) {
        if (bulletCount < 0) {
            throw new IllegalArgumentException("Количество боеприпасов должно быть больше или равно нулю");
        }
        this.bulletCount = bulletCount;
    }

    @Override
    public U apply(U u) {
        return u.shot(this);
    }

    public final int bulletCount;
}
