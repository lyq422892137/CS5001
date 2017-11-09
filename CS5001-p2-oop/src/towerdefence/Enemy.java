package towerdefence;
/**
 * the father class of enemy.
 * */
public class Enemy  {
    /**
     * position of this enemy.
     * */
    protected int eposition = 0;
    /**
     * enemyHp is the healthy point in a game.
     * */
    protected int enemyHp = 1;
    /**
     * type of this enemy.
     * */
    protected String type;
    /**
     * value of this enemy.
     * */
    protected  int earns;
    /**
     * to go or wait.
     * */
    protected int subTimeFlag;
    /**
     * get hp.
     * @return hp
     * */
    public int getHealth() {
        return this.enemyHp;
    }
    /**
     * get position.
     * @return position
     * */
    public int getPosition() {
        return eposition;
    }
    /**
     * be attacked by a tower.
     * @param t is the tower which is attacking the enemy
     * */
    public void hit(Tower t) {
        this.enemyHp = this.enemyHp - t.damage;
    }
    /**
     * go ahead.
     * */
    public void advance() {
        this.subTimeFlag = subTimeFlag;
    }
    /**
     * enemy.
     * */
    public Enemy() {
        this.enemyHp = enemyHp;
        this.type = type;
        this.eposition = eposition;
        this.earns = earns;
        this.subTimeFlag = subTimeFlag;
    }
    /**
     * override.
     * */
    @Override
    public String toString() {
        return ("Type: " + this.type + " HP: " + enemyHp + " Position: " + eposition + " ** ");
    }
}
