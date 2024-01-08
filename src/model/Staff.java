package model;

import java.util.Random;

public class Staff extends Weapon {
    public Staff(String name) {
        super(name);
    }
    //En caso de baston si la suma de 3 randoms del 1 al 20 es superior a 50 el daño será de 100 de lo contrario
    // el daño sera 0;

    @Override
    public int weaponDamage(Player opponent) {
        Random rand = new Random();
        int attackStaff = 0;

        for (int i = 0; i < 3; i++) attackStaff = attackStaff + (rand.nextInt(20) + 1);

        if (attackStaff > 50) attackStaff = 100;
        else attackStaff = 0;

        return attackStaff;
    }
}
