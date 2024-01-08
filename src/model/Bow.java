package model;

import java.util.Random;

public class Bow extends Weapon {
    public Bow(String name) {
        super(name);
    }

    //En caso de arco he creado un random que si toca par el daño será x3 y si toca impar x1
    @Override
    public int weaponDamage(Player opponent) {
        Random rand = new Random();
        int randNum = rand.nextInt(10) + 1;
        int attackBow = rand.nextInt(20) + 1; //El daño será entre 1 y 20
        if (randNum % 2 == 0) attackBow = attackBow * 3;
        return attackBow;
    }
}
