package model;

import java.util.Random;

public class Warrior extends Player {

    public Warrior(String name, int lvl, int stamina, int magic, Weapon weapon, boolean dead) {
        super(name, lvl, stamina, magic, weapon, dead);
    }

    @Override
    public String toString() {
        return getName() + " [lvl=" + getLvl() + ", stamina=" + getStamina() + ", class=" + "Warrior" + ", magic="
                + getMagic() + ", weapon=" + getWeapon().getName() + "]";
    }

    @Override
    public int generateRandomNumberClassAbility() {
        Random rand = new Random();
        int attackWarrior;
        int maxDamage = 0;

        for (int i = 0; i < 2; i++) {
            attackWarrior = (rand.nextInt(10) + 1);
            if (attackWarrior > maxDamage) maxDamage = attackWarrior;
        }
        return maxDamage;
    }

    @Override
    public void damageTaken(Player player, int damageOponentGiven) {
        super.damageTaken(player, damageOponentGiven);
        this.setStamina(this.getStamina() + 2); //Recibir 2 de daños menos es lo mismo que empezar con 2 de vida más
    }
}
