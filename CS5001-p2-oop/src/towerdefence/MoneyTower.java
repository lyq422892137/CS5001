package towerdefence;
/**
 * son class of tower.
 * */
public class MoneyTower extends Tower {
    /**
     * the moneytower only cause damage 1 every round.
     * but it will produce money
     * @param towerpostion is the position of the tower
     * */
    public MoneyTower(int towerpostion) {
        this.damage = 2;
        this.tposition = towerpostion;
        this.typeName = "MoneyTower";
        this.costs = 5;
    }
}
