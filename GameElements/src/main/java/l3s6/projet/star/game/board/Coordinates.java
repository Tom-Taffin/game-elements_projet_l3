package l3s6.projet.star.game.board;

public class Coordinates {
    /**
     * The y coordinate is positive when it goes up, and negative when it goes down.
     */
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates rightCoordinates(){
        return new Coordinates(this.x + 1, this.y);
    }

    public Coordinates leftCoordinates(){
        return new Coordinates(this.x - 1, this.y);
    }

    public Coordinates upCoordinates(){
        return new Coordinates(this.x, this.y + 1);
    }

    public Coordinates downCoordinates(){
        return new Coordinates(this.x, this.y - 1);
    }
}
