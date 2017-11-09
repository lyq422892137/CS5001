package towerdefence;
import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.System.exit;

/**
 * @see Class Game is the main class for this game.
 * @author 170010240
 * this is a tower defense game
 * */
public class Game {
    /**
     * Enemy enemies is the ArrayList used to manipulate enemies.
     * */
    private ArrayList<Enemy> enemies = new ArrayList<>();
    /**
     * Enemy killed is the ArrayList used to store killed enemies.
     */
    private ArrayList<Enemy> killed = new ArrayList<>();
    /**
     * Tower is the ArrayList used to manipulate towers.
     * */
    private ArrayList<Tower> towers = new ArrayList<>();
    /**
     * corridorLength is the path length.
     * */
    private int corridorLength;
    /**
     * budgets is the budgets the player has.
     * */
    private int budgets = 15;
    /**
     * It is the money earned by tower MoneyTower.
     * */
    private int moneyTowerEarns = 0;
    /**
     * This is the main method to run the program.
     * it new a game and start it
     * if the input is not a integer or not in the right range,
     * the system will report errors and stop
     * @param args for getting corridorLength
     * */
    public static void main(final String[] args) {
        try {
            int corridorLength = Integer.parseInt(args[0]);
            if (corridorLength < 10 || corridorLength > 100) {
                System.out.println("The corridorLength should be longer than 10 position and shorter than 100");
                exit(0);
            } else {
                Game game = new Game(corridorLength);
                game.advance();
            }
        } catch (Exception e) {
            System.out.println("Usage: java Game <CorridorLength>");
            e.printStackTrace();
        }
    }
    /**
     * this constructor is used for Game.
     * @param corridorLength is the parameter of the path length.
     * */
    private Game(final int corridorLength) {
        this.corridorLength = corridorLength;
        System.out.println("There are three kinds of Towers: MoneyTower, Catapult, and Slingshot");
        System.out.println("There are four kinds of Enemies: Boss, Theft, Elephant, and Rat");
        System.out.println("Your Initial Budget is " + budgets);
        System.out.println("The method of setting a tower: <TowerType>");
        System.out.println("Ready? Go!");
        for (int i = 0; i < 200; i++) {
            System.out.print("*");
        }
        System.out.println("");
    }
    /**
     * the round of the game.
     * */
    public void advance() {
        Scanner scanTower = new Scanner(System.in);
        String input;
        for (int timeStep = 1; timeStep < corridorLength + 1; timeStep++) {
            /*
            first, the player has one chance to deploy a tower,
            he/she needs to select the tower's type
            0 means do not set towers
            1 means set one Catapult
            2 means set one Slingshot
            3 means set one MoneyTower
            */
            System.out.println("Round: " + timeStep);
            System.out.println("Current budgets: " + budgets);
            System.out.println("Tower: \n" + towers);
            System.out.println("Set Your Tower: 0 Not Set  1 Catapult 2 Slingshot 3 Money Tower");
            input = scanTower.next();
            int ttype = Integer.parseInt(input);
            int towerPosition; //the towers will be deployed from right to left one by one
            towerPosition =  corridorLength - towers.size();
            if (ttype >= 4 || ttype < 0) {
                System.out.println("Sorry, this type of towers do not exist.\n");
                exit(0);
            } else if (ttype == 1) {
                Catapult catapult = new Catapult(towerPosition);
                if (budgets < catapult.costs) {
                    System.out.println("Sorry, you can not set a tower with budgets " + budgets + "\n");
                } else {
                    budgets = budgets - catapult.costs;
                    towers.add(catapult);
                }
            } else if (ttype == 2) {
                Slingshot slingshot = new Slingshot(towerPosition);
                if (budgets < slingshot.costs) {
                    System.out.println("Sorry, you can not set a tower with budgets " + budgets + "\n");
                } else {
                    budgets = budgets - slingshot.costs;
                    towers.add(slingshot);
                }
            } else if (ttype == 3) {
                MoneyTower moneyTower = new MoneyTower(towerPosition);
                if (budgets < moneyTower.costs) {
                    System.out.println("Sorry, you can not set a tower with budgets " + budgets + "\n");
                } else {
                    budgets = budgets - moneyTower.costs;
                    towers.add(moneyTower);
                }
            }
            /*
            second, generate enemies every round
            at the beginning of the game, a boss is produced
            */
            if (timeStep % 2 != 0) {
                for (int i = 0; i < timeStep; i++) {
                    Rat rat = new Rat();
                    rat.subTimeFlag = 0;
                    enemies.add(rat);
                }
            } else if (timeStep % 4 != 0) {
                    Theft theft = new Theft();
                    theft.subTimeFlag = 0;
                    enemies.add(theft);
            } else {
                for (int i = 0; i < timeStep / 2 + 1; i++) {
                    Elephant elephant = new Elephant();
                    elephant.subTimeFlag = 0;
                    enemies.add(elephant);
                }
            }
            if (timeStep == corridorLength / 3 * 2) {
                Boss boss = new Boss();
                boss.subTimeFlag = 0;
                enemies.add(boss);
            }
            /*
             enemies' advance
             */
            for (int i = 0; i < enemies.size(); i++) {
                Enemy enemies1 = enemies.get(i);
                if (enemies1.type.equals("Elephant") && timeStep % 2 == 0) {
                    enemies1.advance();
                    if (enemies1.eposition >= corridorLength) {
                        System.out.println("Sorry! You Lose! Try Again?");
                        summary();
                        exit(1);
                    }
                } else if (enemies1.type.equals("Rat")) {
                    enemies1.advance();
                    if (enemies1.eposition >= corridorLength) {
                        System.out.println("Sorry! You Lose! Try Again?");
                        summary();
                        exit(1);
                    }
                } else if (enemies1.type.equals("Theft")) {
                    /**
                     * every time a theft advances, it will store one currency
                     * */
                    enemies1.advance();
                    if (enemies1.subTimeFlag != 0) {
                        budgets--;
                    }
                    if (enemies1.eposition >= corridorLength) {
                        System.out.println("Sorry! You Lose! Try Again?");
                        summary();
                        exit(1);
                    }
                } else if (enemies1.type.equals("Boss")) {
                    /**
                     * every time a theft advances, it will store one currency
                     * */
                    enemies1.advance();
                    if (enemies1.subTimeFlag != 0) {
                        System.out.println("BOSS IS APPROACHING!!!" + "\n");
                    }
                    if (enemies1.eposition >= corridorLength) {
                        System.out.println("Sorry! You Lose! Try Again?");
                        summary();
                        exit(1);
                    }
                }
            }
            System.out.println("Before the Attack, Current Enemies: (Position 0 means the Enemy has not entered the CorridorLength) \n" + enemies + "\n");
            /*
             the attack method:
             every round the Slingshot attack one enemy
             every three round the Catapult attack one enemy
             */
            for (int j = 0; j < towers.size(); j++) {
                /*
                the first round : the enemies appear, the tower start to attack
                that means, once the tower is set, it attack instantly
                */
                Tower tower1 = towers.get(j);
                if (tower1.typeName.equals("Catapult") && ((timeStep - 1) % 3 == 0) || timeStep == 0) {
                    attackEnemy(tower1, enemies, timeStep);
                } else if (tower1.typeName.equals("Slingshot")) {
                    attackEnemy(tower1, enemies, timeStep);
                }
            }
            System.out.println("Killed Enemies in this round: " + killed + "\n");
            System.out.println("After the Attack, Current Enemies (Position 0 means the Enemy has not entered the CorridorLength): \n" + enemies + "\n");
            for (int m = 0; m < 100; m++) {
                System.out.print("*");
            }
            System.out.print("\n");
            moneyTowerEarns = moneyTowerEarns + moneyTowerWork();
            budgets = budgets + moneyTowerWork();
       }
       System.out.println("Congratulations! You Win!");
        summary();
    }
    /**
     * towers attack enemies by this method.
     * for code reuse
     * towers should attack the enemy who has the highest eposition
     * @param t is the tower
     *          @param enemies is the enemies
     *                         @param timeStep is the round
     * */
    public void attackEnemy(Tower t, ArrayList<Enemy> enemies, int timeStep) {
        t.willFire(timeStep);
        int maxPostion = 0;
        int maxLocation = 0;
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e1 = enemies.get(i);
            if (e1.eposition >= maxPostion) {
                maxPostion = e1.eposition;
                maxLocation = i;
            }
        }
        /*
         the tower attacks the enemy
         */
        Enemy e1 = enemies.get(maxLocation);
        if (t.tposition >= e1.eposition && e1.eposition != 0) {
            if (t.damage > e1.enemyHp) {
                killed.add(e1);
                enemies.remove(e1);
                budgets = budgets + e1.earns;
            } else {
                e1.hit(t);
                if (e1.enemyHp <= 0) {
                    killed.add(e1);
                    enemies.remove(e1);
                    budgets = budgets + e1.earns;
                }
            }
        }
    }
    /**
     * function for MoneyTower.
     * @return the money earned by all MoneyTowers in this round
     * every round the MoneyTower will produce three currencies
     * */
    public int moneyTowerWork() {
        int counter = 0;
        for (int i = 0; i < towers.size(); i++) {
            Tower tower = towers.get(i);
            if (tower.typeName.equals("MoneyTower")) {
                counter++;
            }
        }
        return  counter * 2;
    }
    /**
     * summary the results of the player.
     * */
    public void summary() {
        System.out.println("Summary:");
        System.out.println("Your Money: " + budgets);
        System.out.println("Kill Enemies: " + killed.size());
        System.out.println("Set Towers: " + towers.size());
        System.out.println("Earns from MoneyTower: " + moneyTowerEarns);
    }
}
