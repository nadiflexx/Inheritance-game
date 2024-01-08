package manager;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Player;
import model.Weapon;
import utils.Printer;

public class Manager {

    private static Manager manager;
    private Scanner sc;
    public Player player;
    public Player opponent;
    public int escapeIntents;
    private boolean exit;

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void newGame() {
        sc = new Scanner(System.in);
        escapeIntents = 0;

        System.out.println("Player's name:");
        String name = sc.next();
        System.out.println("Player's weapon name:");

        player = new Player(name, new Weapon(sc.next()));
        opponent = new Player(1);

        startCombat();
    }

    private void startCombat() {
        Printer.getMenuLevel(player, opponent);
        exit = false;
        while (!exit) {
            try {
                Printer.getMenu();

                int option = sc.nextInt();
                int playerAttack;
                int oponentAttack;

                switch (option) {
                    case 1:
                        playerAttack = player.attack(opponent);
                        oponentAttack = opponent.attack(player);
                        player.printResults(playerAttack, opponent, oponentAttack);
                        break;
                    case 2:
                        player.protect(opponent);
                        break;
                    case 3:
                        playerAttack = player.weaponAbility(opponent);
                        oponentAttack = opponent.attack(player);
                        player.printResults(playerAttack, opponent, oponentAttack);
                        break;
                    case 4:
                        playerAttack = player.classAbility(opponent);
                        player.printResults(playerAttack, opponent, 0);
                        break;
                    case 5:
                        escapeIntents++;
                        opponent.attack(player);
                        Printer.printEscapes(escapeIntents);
                        endCombat();
                        break;
                    default:
                        System.out.println("Error. Select a correct option.");
                        break;
                }

                player.chkPlayerIsAlive(opponent);
                if (player.getDead() && option != 5 || opponent.getDead() && option != 5) endCombat();

            } catch (InputMismatchException e) {
                System.out.println("Error. Wrong characters. Try it again.");
                sc.nextLine();
            }
        }
    }

    private void endCombat() {
        boolean leaveMenuEndCombat = false;

        while (!leaveMenuEndCombat) {
            int playerOption = player.endCombatMenu(escapeIntents, opponent);

            if (playerOption == 1) {
                opponent = new Player(player.generateRandomNumberEnemy(player.getLvl()));
                Printer.getMenuLevel(player, opponent);
            } else if (playerOption == 4 && player.getLvl() >= 5) {
                player = player.setClass();
            } else if (playerOption == 5 && player.getLvl() >= 5) {
                player.setWeapon(player.setWeaponClass());
            }

            leaveMenuEndCombat = (playerOption == 1 || playerOption == 3);
            exit = (playerOption == 3 || playerOption == 0);
        }
    }
}
