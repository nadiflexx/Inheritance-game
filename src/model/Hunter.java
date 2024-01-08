package model;

import java.util.Random;

public class Hunter extends Player {

    public Hunter(String name, int lvl, int stamina, int magic, Weapon weapon, boolean dead) {
        super(name, lvl, stamina, magic, weapon, dead);
    }

    @Override
    public String toString() {
        return getName() + " [lvl=" + getLvl() + ", stamina=" + getStamina() + ", class=" + "Hunter" + ", magic="
                + getMagic() + ", weapon=" + getWeapon().getName() + "]";
    }

    @Override
    public int generateRandomNumberClassAbility() {
        Random rand = new Random();
        int attackHunter = 0;
        for (int i = 0; i < 6; i++) attackHunter = attackHunter + (rand.nextInt(3) + 1);
        return attackHunter;
    }

    @Override
    public void resetStats(Player opponent) {
        super.resetStats(opponent);
        this.setStamina(this.getStamina() + 10); //Seteamos la vida con 10 más cuando hacemos reset de stats
    }
}
