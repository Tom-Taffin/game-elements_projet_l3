package l3s6.projet.star.game.tile;

public enum Orientation {
    /* Represents the orientation of the tile. */
    NORTH, SOUTH, WEST, EAST;

    /**
     * @return the opposite orientation
     */
    public Orientation rotateHalf(){
        switch (this) {
            case NORTH:
                return Orientation.SOUTH;
            case EAST:
                return Orientation.WEST;
            case SOUTH:
                return Orientation.NORTH;
            default:
                return Orientation.EAST;
        }
    }

    /**
     * @return the next orientation in a counter-clockwise direction
     */
    public Orientation rotateLeft(){
        switch (this) {
            case NORTH:
                return Orientation.WEST;
            case EAST:
                return Orientation.NORTH;
            case SOUTH:
                return Orientation.EAST;
            default:
                return Orientation.SOUTH;
        }
    }

    /**
     * @return the next orientation in a clockwise direction
     */
    public Orientation rotateRight(){
        switch (this) {
            case NORTH:
                return Orientation.EAST;
            case EAST:
                return Orientation.SOUTH;
            case SOUTH:
                return Orientation.WEST;
            default:
                return Orientation.NORTH;
        }
    }
}
