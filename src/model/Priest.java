package model;

import java.util.Random;

public class Priest extends Player {

    public Priest(String name, int lvl, int stamina, int magic, Weapon weapon, boolean dead) {
        super(name, lvl, stamina, magic, weapon, dead);
    }

    @Override
    public String toString() {
        return getName() + " [lvl=" + getLvl() + ", stamina=" + getStamina() + ", class=" + "Priest" + ", magic="
                + getMagic() + ", weapon=" + getWeapon().getName() + "]";
    }

    @Override
    public int generateRandomNumberClassAbility() {
        Random rand = new Random();
        return (rand.nextInt(10) + 1) + this.getLvl();
    }

    @Override
    public int classAbility(Player opponent) {
        this.setMagic(this.getMagic() + 1); //En caso de escoger class ability sumaré 1 más de magic para que solo gaste 1
        return super.classAbility(opponent);

    }
}
