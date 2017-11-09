package towerdefence;
/**
 * This class is the father class of Tower.
 * */
public class Tower {
    /**
     * damage is the damage caused by the tower.
     * */
    protected int damage;
    /**
     * position of this tower.
     * */
    protected int tposition = 1;
    /**
     * name of the tower.
     * */
    protected String typeName;
    /**
     * cost of setting the tower.
     * */
    protected int costs;
    /**
     * get damage.
     * @return damage
     * */
    public int getDamage() {
        return  this.damage;
    }
    /**
     * know the position.
     * @return position
     * */
    public int getPosition() {
        return this.tposition;
    }
    /**
     * fire.
     * @return true
     * @param timeStep is the round
     * */
    public boolean willFire(int timeStep) {
        return true;
    }
    /**
     * the constructor tower.
     * */
    public Tower() {
        this.damage = damage;
        this.tposition = tposition;
        this.costs = costs;
        this.typeName = typeName;
    }
    /**
     * override tostring.
     * */
    @Override
    public String toString() {
        return ("Type: " + this.typeName + " Damage: " + this.damage + " Position: " + this.tposition + " ** ");
    }
}
