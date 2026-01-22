package at.aau.serg.exercises.gamelogic.gameboard;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
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
    private final StringBuilder rowString;

    public GameBoard(List<Enemy> enemies) {
        board = new Type[5][5];
        damageMultiplier = new double[5][5];
        walkingmultiplier = new double[5][5];

        rowString = new StringBuilder();
        random = new Random();
        enemyList = enemies;
        initialize(enemies);
    }

    /**
     * 

    // 🔒 Konstruktor macht NUR Initialisierung, keine Logik
    private GameBoard(List<Enemy> enemies, Random random) {
        this.enemyList = enemies;
        this.random = random;

        this.board = new Type[BOARD_SIZE][BOARD_SIZE];
        this.damageMultiplier = new double[BOARD_SIZE][BOARD_SIZE];
        this.walkingMultiplier = new double[BOARD_SIZE][BOARD_SIZE];
    }

    // 🏭 Factory-Methode mehrere gameboards unabhängig voneinander baubar
    // abgekapselt und übershaubarer auch leichter testbar
    public static GameBoard createRandom(List<Enemy> enemies) {
        GameBoard board = new GameBoard(enemies, new Random());
        board.initializeRandomBoard();
        board.placeEnemies();
        return board;
    }

    // 🎲 Initialisiert Spielfeld
    private void initializeRandomBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            StringBuilder rowString = new StringBuilder();

            for (int col = 0; col < BOARD_SIZE; col++) {
                Type type = Type.values()[random.nextInt(Type.values().length)];
                board[row][col] = type;

                walkingMultiplier[row][col] = type.getWalkingMultiplier();
                damageMultiplier[row][col] = type.getDamageMultiplier();

                rowString.append(type).append(", ");
            }

            LOGGER.log(Level.INFO, rowString::toString);
        }
    }

    // 👾 Platziert Gegner auf dem Board
    private void placeEnemies() {
        for (Enemy enemy : enemyList) {
            if (enemy.getHealth() < 0) {
                continue;
            }

            int x = random.nextInt(BOARD_SIZE);
            int y = random.nextInt(BOARD_SIZE);

            // lässt enemy prüfen ob position vergeben ist
            enemy.setPosition(new Position(x, y));

            double damageMult = damageMultiplier[x][y];
            double speedMult = walkingMultiplier[x][y];

            if (enemy.getType() == Enemy.EnemyType.BOSS) {
                damageMult *= 1.5;
                speedMult *= 1.5;
            }

            // set zugriff anstelle von direct var change, erlaubt klasse selbst zu prüfen und regulieren
            enemy.setDamageMultiplier(damageMult);
            enemy.setSpeedMultiplier(speedMult);
        }
    }

}

     * @param enemies
     */

    private void initialize(List<Enemy> enemies) {
        // Initialize the board with random field types
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                board[row][col] = Type.values()[random.nextInt(Type.values().length)];

               /**
                * private static final int BOARD_SIZE = 5;
                  private static final double WOODS_WALK_MULT = 0.7;
                  es sind zwar fix werte aber es ist hard gecodet, besser wäre vars dafür anzulegen 
                  und fall bedingt auch die möglichkeit zur verfügung zu stellen diesse zu verändern 
                  (beispiel für erleichtertes mooding in der gaming industrie)

                */
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
            LOGGER.log(Level.INFO, () -> rowString.toString());

        }

        // place the enemies
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            if (enemy.health > 0) {
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
    /**
     * möglichkeit enums mit Verhalten zu erstellen
     * enum Type {
            GRASS(1,1),
            WOODS(0.7,1.2),
            ROCK(1,1),
            SPECIAL(1,2);

            final double walkMult;    defiiert verweils die Werte von Grass, Woods, etc
            final double dmgMult;

             Type(double walkingMultiplier, double damageMultiplier) {
                this.walkingMultiplier = walkingMultiplier;
                this.damageMultiplier = damageMultiplier;
            }

            public double getWalkingMultiplier() {
                return walkingMultiplier;
            }

            public double getDamageMultiplier() {
                return damageMultiplier;
            }
        }

        möglicher zugriff ohne switch gebrauch 
        Type fieldType = board[row][col]; oder Type type = Type.WOODS; array [0,1,2,3] woods 1

        walkingmultiplier[row][col] = fieldType.getWalkingMultiplier();
        damageMultiplier[row][col] = fieldType.getDamageMultiplier();

     */
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
     * danach Startwert = 1 → Cyclomatic Complexity = 1 + 9 = 10
     */
