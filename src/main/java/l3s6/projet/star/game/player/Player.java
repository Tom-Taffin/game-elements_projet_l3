package l3s6.projet.star.game.player;

import l3s6.projet.star.game.meeple.NoMeepleException;

public class Player {
    private final String id;
    private int score;
    private int nbBlames;
    private int nbMeeples;

    public Player(String id, int nbMeeples) {
        this.id = id;
        this.score = 0;
        this.nbBlames = 0;
        this.nbMeeples = nbMeeples;
    }

    public int getNumberOfBlames(){
        return nbBlames;
    }

    public int getScore() {
        return score;
    }

    public String getID() {
        return id;
    }

    public void addPoints(int points){
        this.score += points;
    }

    public void blame(){
        this.nbBlames += 1;
    }

    public void incrementMeepleCount(){
        this.nbMeeples += 1;
    }

    public void decrementMeepleCount() throws NoMeepleException{
        System.out.println("[DEBUG] " + this.id + " meeples: " + this.nbMeeples + " → " + (this.nbMeeples - 1));
        if (this.nbMeeples <= 0) {
            throw new NoMeepleException("Player has no meeples left");
        }
        this.nbMeeples -= 1;
    }

    public int getNbMeeples() {
        return nbMeeples;
    }

    public boolean hasMeeples(){
        return this.nbMeeples > 0;
    }
}
