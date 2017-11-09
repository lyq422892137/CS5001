package towerdefence;
/**
 * the boss.
 * */
public class Boss extends Enemy {
    /**
     * the boss are very strong.
     * */
    public Boss() {
        this.enemyHp = 15;
        this.type = "Boss";
        this.earns = 30;
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
