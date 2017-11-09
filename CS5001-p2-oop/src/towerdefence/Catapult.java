package towerdefence;
/**
 * ccatapult.
 * */
public class Catapult extends Tower {
    /**
     * catapult.
     * @param towerpostion is the position of the tower
     * */
    public Catapult(int towerpostion) {
        this.damage = 5;
        this.tposition = towerpostion;
        this.typeName = "Catapult";
        this.costs = 7;
    }
}

