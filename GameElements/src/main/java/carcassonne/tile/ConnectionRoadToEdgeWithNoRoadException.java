package carcassonne.tile;

public class ConnectionRoadToEdgeWithNoRoadException extends RuntimeException {
    public ConnectionRoadToEdgeWithNoRoadException(String message) {
        super(message);
    }
}
