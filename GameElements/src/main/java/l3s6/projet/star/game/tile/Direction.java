package l3s6.projet.star.game.tile;

public enum Direction {
    /* Represents the direction of the edge */
    TOP, RIGHT, BOTTOM, LEFT;

    /**
     * @return the old direction of something before the orientation
     */
    public Direction getOldDirection(Orientation orientation){
        switch (orientation) {
            case NORTH:
                return this;
            case EAST:
                return this.toLeft();
            case SOUTH:
                return this.toOpposite();
            default:
                return this.toRight();
        }
    }

    /**
     * @return the new direction of something after the orientation
     */
    public Direction getNewDirection(Orientation orientation){
        switch (orientation) {
            case NORTH:
                return this;
            case EAST:
                return this.toRight();
            case SOUTH:
                return this.toOpposite();
            default:
                return this.toLeft();
        }
    }
    
    /**
     * @return the opposite direction
     */
    public Direction toOpposite(){
        switch (this) {
            case TOP:
                return Direction.BOTTOM;
            case RIGHT:
                return Direction.LEFT;
            case LEFT:
                return Direction.RIGHT;
            default:
                return Direction.TOP;
        }
    }

    /**
     * @return the next direction in a counter-clockwise direction
     */
    public Direction toLeft(){
        switch (this) {
            case TOP:
                return Direction.LEFT;
            case RIGHT:
                return Direction.TOP;
            case LEFT:
                return Direction.BOTTOM;
            default:
                return Direction.RIGHT;
        }
    }

    /**
     * @return the next direction in a clockwise direction
     */
    public Direction toRight(){
        switch (this) {
            case TOP:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.BOTTOM;
            case LEFT:
                return Direction.TOP;
            default:
                return Direction.LEFT;
        }
    }
}
