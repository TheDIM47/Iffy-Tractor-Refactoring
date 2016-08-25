package com.juliasoft.game.intf;

/**
 * Направление
 * clockWise() - вернет новое направление после поворота по-часовой стрелке
 * counterClockWise() - вернет новое направление после поворота против часовой стрелки
 */
public enum Orientation {

    NORTH {
        @Override
        public Orientation clockWise() {
            return EAST;
        }

        @Override
        public Orientation counterClockWise() {
            return WEST;
        }
    },
    WEST {
        @Override
        public Orientation clockWise() {
            return NORTH;
        }

        @Override
        public Orientation counterClockWise() {
            return SOUTH;
        }
    },
    SOUTH {
        @Override
        public Orientation clockWise() {
            return WEST;
        }

        @Override
        public Orientation counterClockWise() {
            return EAST;
        }
    },
    EAST {
        @Override
        public Orientation clockWise() {
            return SOUTH;
        }

        @Override
        public Orientation counterClockWise() {
            return NORTH;
        }
    };

    public abstract Orientation clockWise();

    public abstract Orientation counterClockWise();
}
