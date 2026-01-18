package at.aau.serg.exercises.gamelogic.gameboard;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class GameBoard {

    private final Type[][] board;
    
    // Damage multipliers for different attack types
    private final double[][] damageMultiplier;

    // Movement multipliers when walking
    private final double[][] walkingmultiplier;
    
    // initialize radnom at first to not get always new random with fakeran var
    private final Random random;
    private final List<Enemy> enemyList;
    private static final Logger LOGGER = Logger.getLogger(GameBoard.class.getName());
    private StringBuilder rowString;

    public GameBoard(List<Enemy> enemies) {
        board = new Type[5][5];
        damageMultiplier = new double[5][5];
        walkingmultiplier = new double[5][5];

        rowString = new StringBuilder();
        random = new Random();
        enemyList = enemies;
        initialize(enemies);
    }

    private void initialize(List<Enemy> enemies) {
        // Initialize the board with random field types
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                board[row][col] = Type.values()[random.nextInt(Type.values().length)];

                switch (board[row][col]) {
                    case GRASS:
                    case ROCK:
                        walkingmultiplier[row][col] = 1;
                        damageMultiplier[row][col] = 1;
                        break;
                    case WOODS:
                        walkingmultiplier[row][col] = .7;
                        damageMultiplier[row][col] = 1.2;
                        break;
                    case SPECIAL:
                        damageMultiplier[row][col] = 2;
                        break;
                    default:
                        break;
                }

             rowString.append(board[row][col]).append(", ");
            }
            LOGGER.info(rowString.toString());
        }

        // place the enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.health == 0) {
                continue;
            } else {
                var x = random.nextInt(5);
                var y = random.nextInt(5);
                enemy.pos = new Position(x, y);

                if (enemy.type == Enemy.EnemyType.BOSS) {
                    enemy.damageMult = damageMultiplier[x][y] * 1.5;
                    enemy.speedMult = walkingmultiplier[x][y] * 1.5;
                } else {
                    enemy.damageMult = damageMultiplier[x][y];
                    enemy.speedMult = walkingmultiplier[x][y];
                }
            }
        }

    }

    // Enum for the field types
    public enum Type {
        GRASS,
        WOODS,
        ROCK,
        SPECIAL
    }

}
/**
 * Die Cyclomatic Complexity 𝑉(𝐺) wird wie folgt definiert:
    𝑉(𝐺)=𝐸−𝑁+2𝑃

    E = Anzahl der Kanten im Kontrollflussgraphen
    N = Anzahl der Knoten im Kontrollflussgraphen
    P = Anzahl der zusammenhängenden Teile (für Methoden typischerweise 1)
 */

    /**
     * davor Startwert = 1 → Cyclomatic Complexity = 1 + 9 = 10
     */
