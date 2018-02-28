import java.util.Objects;

/**
 * Class that models a Monster for the Adventure game.
 *
 * @author diproray
 */
public class Monster {

  // Member variables:
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String name;
  private double attack;
  private double defense;
  private double health;

  // Constructor.
  /**
   * . Constructor for a Monster object
   *
   * @param name the name of the Monster
   * @param attack the attack value
   * @param defense the defense value
   * @param health the health value
   */
  public Monster(String name, double attack, double defense, double health) {

    this.name = name;
    this.attack = attack;
    this.defense = defense;
    this.health = health;
  }

  // Setters.
  /**
   * . Setter for monster health value
   *
   * @param health the health value to be set
   */
  public void setHealth(double health) {
    this.health = health;
  }

  // Getters.

  /**
   * . Getter for name
   *
   * @return the monster name
   */
  public String getName() {
    return name;
  }

  /**
   * . Getter for attack value
   *
   * @return the monster's attack value
   */
  public double getAttack() {
    return attack;
  }

  /**
   * . Getter for defense value
   *
   * @return the monster's defense value
   */
  public double getDefense() {
    return defense;
  }

  /**
   * . Getter for healht value
   *
   * @return the monster's health value
   */
  public double getHealth() {
    return health;
  }

  /**
   * . Equals() method overwritten for testing purposes.
   *
   * @param o the object to be compared to
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
    Monster monster = (Monster) o;
    return Double.compare(monster.attack, attack) == 0
        && Double.compare(monster.defense, defense) == 0
        && Double.compare(monster.health, health) == 0
        && Objects.equals(name, monster.name);
  }
}
