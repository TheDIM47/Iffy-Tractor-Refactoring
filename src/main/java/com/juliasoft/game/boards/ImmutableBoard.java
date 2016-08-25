package com.juliasoft.game.boards;

import com.juliasoft.game.intf.Action;
import com.juliasoft.game.intf.Board;
import com.juliasoft.game.intf.Unit;
import com.juliasoft.game.ex.OutOfTheBoardException;
import com.juliasoft.game.intf.UnitWithPosition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Иммутабельная реализация "доски"
 * На каждый шаг порождает новую копию доски с новым состоянием
 * <p/>
 * Можно было бы добавить:
 * MutableBoard - игровое поле, меняющее состояние своих юнитов (apply(){ ... return this; })
 * Вынести общие методы в AbstractBoard
 * т.е.:
 * Board -> AbstractBoard -> MutableBoard
 * -> ImmutableBoard
 */
public class ImmutableBoard<U extends Unit<U>, A extends Action<U>> implements Board<U,A> {

    public ImmutableBoard(Collection<U> units, int size) {
        if (units == null) {
            throw new IllegalArgumentException("Список юнитов не должен быть null");
        }
        if (size < 1) {
            throw new IllegalArgumentException("Размерность доски должна быть больше нуля");
        }
        this.units.addAll(units);
        this.size = size;
        this.topRight = new Position(size, size);
    }

    @Override
    public Collection<U> getUnits() {
        return units;
    }

    @Override
    public Board apply(A action) {
        // Если сделать вызов как return apply(Position.ZeroPosition, topRight, action);
        // то "ветер" (юниты без позиции) будет проигнорирован - приходится дублировать
        final List<U> newUnits = units.stream()
                .map(u -> { try { return action.apply(u); } catch (Exception x) { return u; }})
//                .map(action::apply) // приводит к ClassCastException: units.Stone cannot be cast to intf.UnitWithOrientation
                .map(this::checkPosition)
                .collect(Collectors.toList());
        return new ImmutableBoard<>(newUnits, size);
    }

    @Override
    public Board apply(Rectangle rect, A action) {
        final Stream<U> unhandled = units.stream().filter(unit -> !inRect(rect, unit));
        final Stream<U> handled = units.stream()
                .filter(unit -> inRect(rect, unit))
                .map(u -> { try { return action.apply(u); } catch (Exception x) { return u; }})
//                .map(action::apply) // приводит к ClassCastException: units.Stone cannot be cast to intf.UnitWithOrientation
                .map(this::checkPosition);
        return new ImmutableBoard<>(Stream.concat(handled, unhandled).collect(Collectors.toList()), size);
    }

    private boolean inRect(Rectangle rect, U unit) {
        if (unit instanceof UnitWithPosition) {
            final Position p = ((UnitWithPosition) unit).getPosition();
            return Position.inRect(p, rect);
        } else {
            return true;
        }
    }

    /**
     * Exception в случае выхода за границы
     * Также возможны варианты реализации:
     * - остаться на месте
     * - отменить все перемещения
     * - убрать юнит с доски
     *
     * p - [новая] позиция
     * @return true если позиция валидна
     */
    // Обработка ошибок позиционирования - выход за границы доски или попадание нескольких юнитов в одну точку
    private U checkPosition(U unit) {
        final boolean isValidPosition = !(unit instanceof UnitWithPosition) || checkPosition(((UnitWithPosition) unit).getPosition());
        if (isValidPosition) {
            return unit;
        } else
            throw new OutOfTheBoardException((UnitWithPosition) unit);
    }

    private boolean checkPosition(Position p) {
        return (p.x >= 0 && p.x < size && p.y >= 0 && p.y < size);
    }

    public int getSize() {
        return size;
    }

    private final List<U> units = new ArrayList<>();
    private final Position topRight;
    private final int size;
}
