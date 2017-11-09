package towerdefence;
/**
 * rat.
 * */
public class Rat extends Enemy {
    /**
     * rat.
     * */
    public Rat() {
        this.enemyHp = 1;
        this.type = "Rat";
        this.earns = 1;
    }
    /**
     * go ahead.
     * */
    public void advance() {
        if (subTimeFlag != 0) {
            this.eposition = eposition + 2;
        } else if (subTimeFlag == 0) {
            subTimeFlag++;
        }
    }
}
