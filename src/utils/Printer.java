package utils;

import model.Player;

public class Printer {

    public static void getMenu() {
        System.out.println();
        System.out.println("Choose one option: ");
        System.out.println();
        System.out.println(" -------MENU---------");
        System.out.println("|  1) ATTACK.        |");
        System.out.println("|  2) PROTECT.       |");
        System.out.println("|  3) WEAPON ABILITY.|");
        System.out.println("|  4) CLASS ABILITY. |");
        System.out.println("|  5) ESCAPE.        |");
        System.out.println(" --------------------");
        System.out.println();
    }

    public static void getMenuLevel(Player player, Player oponent) {
        System.out.println("--------GAME---------");
        System.out.println("-------PLAYER--------");
        System.out.println(player.getName() + " of level " + player.getLvl());
        System.out.println("--------ENEMY--------");
        System.out.println(oponent.getName() + " of level " + oponent.getLvl());
        System.out.println("---------------------");
        System.out.println("---------------------");
    }

    public static void printMenuEscape(int lvl) {
        System.out.println();
        System.out.println(" ------COMBAT ENDED------");
        System.out.println("|  1) New enemy.         |");
        System.out.println("|  2) See my statistics. |");
        System.out.println("|  3) Give up.           |");
        if (lvl >= 5) System.out.println("|  4) Change class.      |");
        if (lvl >= 5) System.out.println("|  5) Change weapon.     |");
        System.out.println(" ------------------------");
        System.out.println();
    }

    public static void printWeapon(boolean weapon) {
        if (weapon) {
            System.out.println("--WEAPON ACTIVATED---");
            System.out.println("---------------------");
        } else {
            System.out.println("--WEAPON ACTIVATED---");
            System.out.println("------Magic: -1------");
            System.out.println("----WEAPON FAILED----");
        }
    }

    public static void printEscapes(int intents) {
        if (intents != 6) {
            System.out.println("-------ESCAPE " + intents + "/5--------");
        } else {
            System.out.println("-----NO ESCAPES LEFT-----");
            System.out.println("-----CLOSING THE GAME----");
        }
        System.out.println("-------------------------");
    }

    public static void printClasses() {
        System.out.println(" -------CLASS--------");
        System.out.println("|  1) PRIEST.        |");
        System.out.println("|  2) WARRIOR.       |");
        System.out.println("|  3) HUNTER.        |");
        System.out.println(" --------------------");
        System.out.println();
    }

    public static void printClassAttack(boolean weapon) {
        if (weapon) {
            System.out.println("---CLASS ACTIVATED---");
            System.out.println("---------------------");
        } else {
            System.out.println("---CLASS ACTIVATED---");
            System.out.println("------Magic: -1------");
            System.out.println("----CLASS FAILED----");
        }
    }

    public static void printWeapons() {
        System.out.println(" -------WEAPON-------");
        System.out.println("|  1) BOW.           |");
        System.out.println("|  2) STAFF.         |");
        System.out.println("|  3) SWORD.         |");
        System.out.println(" --------------------");
        System.out.println();
    }

}
