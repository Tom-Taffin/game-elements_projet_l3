package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.player.Player;

public class Meeple {
    private Player player;

    public Meeple(Player player) {
        this.player = player;
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
