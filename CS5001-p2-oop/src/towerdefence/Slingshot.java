package towerdefence;
/**
 * slingshot.
 * */
public class Slingshot extends Tower {
    /**
     * slingshot.
     * @param towerpostion is the position of tower
     * */
    public Slingshot(int towerpostion) {
        this.damage = 1;
        this.tposition = towerpostion;
        this.typeName = "Slingshot";
        this.costs = 2;
    }
}
