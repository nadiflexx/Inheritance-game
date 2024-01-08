package model;

import java.util.Random;

public class Weapon {
    private final String name;

    public Weapon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int weaponDamage(Player oponent) { //En este caso el parametro del oponent no se usa pero en la herencia sword si
        Random rand = new Random();
        int attackWeapon = 0;

        for (int i = 0; i < 3; i++) {
            attackWeapon = attackWeapon + (rand.nextInt(3) + 1);
        }
        return attackWeapon;
    }
}
