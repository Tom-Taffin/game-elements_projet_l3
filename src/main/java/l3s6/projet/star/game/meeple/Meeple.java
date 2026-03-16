package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.board.Coordinates;
import l3s6.projet.star.game.player.Player;

public class Meeple {
    private Player player;
    private Coordinates coordinates;

    public Meeple(Player player, Coordinates coordinates) {
        this.player = player;
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void incrementPlayerMeeple(){
        this.player.incrementMeepleCount();
    }

    public void decrementPlayerMeeple() {
        this.player.decrementMeepleCount();
    }
}
