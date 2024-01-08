package model;

import java.util.Random;

public class Sword extends Weapon {
    public Sword(String name) {
        super(name);
    }

    //Sword tendrá 2 ataques. El básico y el crítico. El basico simplemente será un random de 3 numeros del 1 al 10.
    //El critico matará al enemigo directamente. El crítico sucederá cuando el numero randomCritico acabe en 0.
    @Override
    public int weaponDamage(Player opponent) {
        Random rand = new Random();
        int randomCritico = rand.nextInt(100) + 1;
        int attackSword = 0;

        if (randomCritico % 10 == 0) attackSword = opponent.getStamina();
        else {
            for (int i = 0; i < 3; i++) {
                attackSword = attackSword + (rand.nextInt(10) + 1);
            }
        }
        return attackSword;
    }
}
