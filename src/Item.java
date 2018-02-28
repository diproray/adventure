import java.util.Objects;

/**
 * Class that models an item for the Adventure game.
 *
 * @author diproray
 */
public class Item {

  // Member variables:
  // Names could be more descriptive, but
  // I am bound to use the names used in the JSON file (gson requirement).
  private String name;
  private double damage;

  // Constructor
  /**
   * . Constructor for Item object
   *
   * @param name the item name
   * @param damage the item damage
   */
  public Item(String name, double damage) {
    this.name = name;
    this.damage = damage;
  }

  // Getters.
  /**
   * . Getter for item's name
   *
   * @return a String - the item name
   */
  public String getName() {
    return name;
  }

  /**
   * . Getter for item damae
   *
   * @return a double value
   */
  public double getDamage() {
    return damage;
  }

  /**
   * . Equal() method overwritten for testing purposes.
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
    Item item = (Item) o;
    return Double.compare(item.damage, damage) == 0 && Objects.equals(name, item.name);
  }
}
