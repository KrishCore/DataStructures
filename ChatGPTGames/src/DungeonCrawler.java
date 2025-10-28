import java.util.Random;
import java.util.Scanner;

public class DungeonCrawler {

    static final int SIZE = 5;
    static final char EMPTY = '.';
    static final char PLAYER = 'P';
    static final char ENEMY = 'E';
    static final char EXIT = 'X';
    static final char POTION = '+';

    static char[][] map = new char[SIZE][SIZE];
    static int playerRow = 0, playerCol = 0;
    static int playerHP = 20;
    static int potions = 0;

    static Random rand = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupMap();
        gameLoop();
    }

    static void setupMap() {
        // Fill map with empty tiles
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                map[i][j] = EMPTY;

        // Place enemies
        for (int i = 0; i < 5; i++) {
            int r = rand.nextInt(SIZE);
            int c = rand.nextInt(SIZE);
            if (r == 0 && c == 0) continue;
            map[r][c] = ENEMY;
        }

        // Place potions
        for (int i = 0; i < 3; i++) {
            int r = rand.nextInt(SIZE);
            int c = rand.nextInt(SIZE);
            if (map[r][c] == EMPTY)
                map[r][c] = POTION;
        }

        // Place exit
        int exitR, exitC;
        do {
            exitR = rand.nextInt(SIZE);
            exitC = rand.nextInt(SIZE);
        } while (map[exitR][exitC] != EMPTY);
        map[exitR][exitC] = EXIT;
    }

    static void gameLoop() {
        while (true) {
            printMap();
            System.out.println("HP: " + playerHP + " | Potions: " + potions);
            System.out.print("Move (WASD), H to heal: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("H")) {
                if (potions > 0) {
                    playerHP = Math.min(playerHP + 10, 20);
                    potions--;
                    System.out.println("You used a potion and healed 10 HP!");
                } else {
                    System.out.println("You have no potions!");
                }
                continue;
            }

            int newRow = playerRow;
            int newCol = playerCol;

            switch (input) {
                case "W": newRow--; break;
                case "S": newRow++; break;
                case "A": newCol--; break;
                case "D": newCol++; break;
                default:
                    System.out.println("Invalid input!");
                    continue;
            }

            if (newRow < 0 || newRow >= SIZE || newCol < 0 || newCol >= SIZE) {
                System.out.println("You hit a wall!");
                continue;
            }

            playerRow = newRow;
            playerCol = newCol;

            handleTile();
            if (playerHP <= 0) {
                System.out.println("You died! Game over.");
                break;
            }
        }
    }

    static void handleTile() {
        char tile = map[playerRow][playerCol];

        if (tile == ENEMY) {
            System.out.println("You encountered an enemy!");
            int enemyHP = 10;

            while (enemyHP > 0 && playerHP > 0) {
                int playerDmg = rand.nextInt(6) + 1;
                int enemyDmg = rand.nextInt(4) + 1;

                enemyHP -= playerDmg;
                playerHP -= enemyDmg;

                System.out.println("You hit enemy for " + playerDmg + " damage.");
                System.out.println("Enemy hits you for " + enemyDmg + " damage.");
            }

            if (playerHP > 0) {
                System.out.println("You defeated the enemy!");
                map[playerRow][playerCol] = EMPTY;
            }
        } else if (tile == POTION) {
            potions++;
            System.out.println("You found a healing potion!");
            map[playerRow][playerCol] = EMPTY;
        } else if (tile == EXIT) {
            System.out.println("You found the exit and escaped the dungeon! You win!");
            System.exit(0);
        }
    }

    static void printMap() {
        System.out.println("\nDungeon:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == playerRow && j == playerCol) {
                    System.out.print(PLAYER + " ");
                } else {
                    System.out.print(EMPTY + " ");
                }
            }
            System.out.println();
        }
    }
}
