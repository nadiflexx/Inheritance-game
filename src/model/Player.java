package model;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import utils.Printer;

public class Player {
    private final String name;
    private int lvl;
    private int stamina;
    private int magic;
    private Weapon weapon;
    private boolean dead;

    public Player(String name, int lvl, int stamina, int magic, Weapon weapon, boolean dead) {
        this.name = name;
        this.lvl = lvl;
        this.stamina = stamina;
        this.magic = magic;
        this.weapon = weapon;
        this.dead = dead;
    }

    public Player(int level) {
        this.name = "Enemy";
        this.lvl = level;
        this.stamina = lvl * 10;
        this.magic = lvl * 4;
        this.weapon = new Weapon("Crasher");
        this.dead = false;
    }

    public Player(String name, Weapon weapon) {
        this.name = name;
        this.lvl = 1;
        this.stamina = 10;
        this.magic = 4;
        this.weapon = weapon;
        this.dead = false;
    }

    public String getName() {
        return name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = this.lvl + lvl;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public boolean getDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int generateRandomNumberEnemy(int level) {
        Random rand = new Random();
        return rand.nextInt((level + 2) - level + 1) + level;
    } //Jugabilidad escalable (entre el level del jugador y 2 más)

    public int generateRandomNumberAttack() {
        Random rand = new Random();
        return (rand.nextInt(6) + 1) + this.getLvl();
    }

    public int generateRandomNumberClassAbility() {
        Random rand = new Random();
        double attack = 0;
        for (int i = 0; i < 3; i++) attack = attack + rand.nextInt(6) + 1;

        return (int) attack / 3;
    }

    public int attack(Player player) {
        int attackGiven = generateRandomNumberAttack();
        damageTaken(player, attackGiven);

        return attackGiven;
    }

    public void damageTaken(Player player, int damageOpponentGiven) {
        player.setStamina(player.getStamina() - damageOpponentGiven);
        if (player.getStamina() <= 0) player.setStamina(0);
    }

    public void printResults(int damagePlayerGiven, Player opponent, int damageOpponentGiven) {
        System.out.println("--------DAMAGE-------");
        System.out.println(this.getName() + " receive " + damageOpponentGiven + "! Remaining " + this.getStamina());
        System.out.println(opponent.getName() + " receive " + damagePlayerGiven + "! Remaining " + opponent.getStamina());
        System.out.println("---------------------");
        System.out.println("---------------------");
    }

    public void protect(Player enemy) {
        printResults(0, enemy, 0);
    }

    public int weaponAbility(Player opponent) {
        int weaponDamage = 0;

        if (magic <= 0) {
            Printer.printWeapon(false);
            damageTaken(opponent, weaponDamage);
        } else {
            Printer.printWeapon(true);
            damageTaken(opponent, weaponDamage = weapon.weaponDamage(opponent));
            this.setMagic(this.getMagic() - 1);
        }

        return weaponDamage;
    }

    public int classAbility(Player opponent) {
        int attackGiven = 0;

        if (magic <= 0) {
            Printer.printClassAttack(false);
            damageTaken(opponent, attackGiven);
        } else {
            Printer.printClassAttack(true);
            damageTaken(opponent, attackGiven = generateRandomNumberClassAbility());
            this.setMagic(this.getMagic() - 2);
        }

        return attackGiven;
    }

    public void chkPlayerIsAlive(Player opponent) {
        //Siempre que el enemigo este muerto y el jugador vivo se subira de lvl (en caso de empate no sube lvl)
        if (opponent.getStamina() <= 0 && this.getStamina() > 0) {
            opponent.setDead(true);
            this.setLvl(1);
        }

        if (this.getStamina() <= 0) {
            this.setDead(true);
        }
    }

    public void resetStats(Player opponent) {
        this.setDead(false);
        this.setMagic(this.lvl * 4);
        this.setStamina(this.lvl * 10);
        opponent.setDead(false);
        opponent.setMagic(opponent.getLvl() * 4);
        opponent.setStamina(opponent.getLvl() * 10);
    }

    public int endCombatMenu(int intent, Player opponent) {
        resetStats(opponent);
        Scanner sc = new Scanner(System.in);
        int option = 0;

        if (intent != 6) {
            try {
                Printer.printMenuEscape(getLvl());

                option = sc.nextInt();

                switch (option) {
                    case 2:
                        System.out.println(this);
                        break;
                    case 3:
                        giveUp();
                        break;
                    case 4:
                    case 5:
                        if (getLvl() < 5) System.out.println("Error. Select a correct option.");
                        break;
                    default:
                        System.out.println("Error. Select a correct option.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.print("Error. Wrong characters.");
                sc.nextLine();
            }
        }
        return option;
    }

    private int changeClassMenu() {
        int classSelect = 0;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            Printer.printClasses();
            try {
                classSelect = sc.nextInt();
                if (classSelect < 1 || classSelect > 3) System.out.println("Error. Select an option between 1 and 3:");
                else {
                    System.out.println("Class changed successfully.");
                    exit = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Wrong characters. Try again.");
                sc.nextLine();
            }
        }
        return classSelect;
    }

    private int changeWeaponMenu() {
        int weaponSelected = 0;
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            Printer.printWeapons();
            try {
                weaponSelected = sc.nextInt();
                if (weaponSelected < 1 || weaponSelected > 3)
                    System.out.println("Error. Select an option between 1 and 3:");
                else {
                    System.out.println("Weapon changed successfully.");
                    exit = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error. Wrong characters. Try again.");
                sc.nextLine();
            }
        }
        return weaponSelected;
    }

    public Player setClass() {
        int classSelect = changeClassMenu();
        Player playerClass = null;

        switch (classSelect) {
            case 1:
                playerClass = new Priest(name, lvl, stamina, magic, weapon, dead);
                break;
            case 2:
                playerClass = new Warrior(name, lvl, stamina, magic, weapon, dead);
                break;
            case 3:
                playerClass = new Hunter(name, lvl, stamina, magic, weapon, dead);
                break;
            default:
                System.out.println("Error. Please select and option between 1 and 3");
                break;
        }
        return playerClass;
    }

    public Weapon setWeaponClass() {
        int weaponSelected = changeWeaponMenu();
        Weapon weaponClass = null;

        switch (weaponSelected) {
            case 1:
                weaponClass = new Bow("Bow");
                break;
            case 2:
                weaponClass = new Staff("Staff");
                break;
            case 3:
                weaponClass = new Sword("Sword");
                break;
            default:
                System.out.println("Error. Please select and option between 1 and 3");
                break;
        }
        return weaponClass;
    }

    private void giveUp() {
        System.out.println("Thanks for playing!!!");
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(this);
        System.out.println("---------------------");
        System.out.println("---------------------");
    }

    @Override
    public String toString() {
        return name + " [lvl=" + lvl + ", stamina=" + stamina + ", magic=" + magic + ", weapon="
                + weapon.getName() + "]";
    }
}
