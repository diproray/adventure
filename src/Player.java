import java.util.ArrayList;
import java.util.Objects;

/**
 * Class modelling a Player for the Adventure game.
 *
 * @author diproray
 */
public class Player {

  // Member variables:
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String name;
  private ArrayList<Item> items;
  private double attack;
  private double defense;
  private double health;
  private int level;

  // Setters.
  /**
   * . Setter for player health value
   *
   * @param health the health value to be set
   */
  public void setHealth(double health) {
    this.health = health;
  }

  /**
   * . Setter for player level value
   *
   * @param level the level
   */
  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * . Setter for player attack value
   *
   * @param attack the attack value
   */
  public void setAttack(double attack) {
    this.attack = attack;
  }

  /**
   * . Setter for player defense value
   *
   * @param defense the defense value
   */
  public void setDefense(double defense) {
    this.defense = defense;
  }
  /**
   * . Getter for name
   *
   * @return the player's name
   */
  public String getName() {
    return name;
  }

  /**
   * . Getter for Array List of items
   *
   * @return the player's list of items
   */
  public ArrayList<Item> getItems() {
    return items;
  }

  /**
   * . Getter for player's attack value
   *
   * @return the attack value
   */
  public double getAttack() {
    return attack;
  }

  /**
   * . Getter for defense value
   *
   * @return the player's defense value
   */
  public double getDefense() {
    return defense;
  }

  /**
   * . Getter for health value
   *
   * @return the player's health value
   */
  public double getHealth() {
    return health;
  }

  /**
   * . Getter for level
   *
   * @return the player's level
   */
  public int getLevel() {
    return level;
  }

  /**
   * . Constructor for a player object
   *
   * @param name the name
   * @param items the player's items
   * @param attack the player's attack value
   * @param defense the player's defense value
   * @param health the player's health value
   * @param level the player's level value
   */
  public Player(
      String name, ArrayList<Item> items, double attack, double defense, double health, int level) {
    this.name = name;
    this.items = items;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
    this.level = level;
  }

  /**
   * . Equals() method overwritten for testing purposes.
   *
   * @param o the object to be compared with
   * @return a boolean value
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return Double.compare(player.attack, attack) == 0
        && Double.compare(player.defense, defense) == 0
        && Double.compare(player.health, health) == 0
        && level == player.level
        && Objects.equals(name, player.name)
        && Objects.equals(items, player.items);
  }
}
