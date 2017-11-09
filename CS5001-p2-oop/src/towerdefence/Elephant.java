package towerdefence;
/**
 * elephant.
 * */
public class Elephant extends Enemy {
    /**
     * elephant.
     * */
    public Elephant() {
        this.enemyHp = 10;
        this.type = "Elephant";
        this.earns = 10;
    }
    /**
     * go ahead.
     * */
    public void advance() {
        if (subTimeFlag != 0) {
            this.eposition = eposition + 1;
        } else if (subTimeFlag == 0) {
            subTimeFlag++;
        }
    }
}
