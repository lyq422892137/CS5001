package towerdefence;
/**
 * theft class.
 * */
public class Theft extends Enemy {
    /**
     * theft is a weak but annoying enemy.
     * it stores 1 money every round
     * */
    public Theft() {
        this.enemyHp = 1;
        this.type = "Theft";
        this.earns = 8;
    }
    /**
     * it runs fast.
     * */
    public void advance() {
        if (subTimeFlag != 0) {
            this.eposition = eposition + 3;
        } else if (subTimeFlag == 0) {
            subTimeFlag++;
        }
    }
}
