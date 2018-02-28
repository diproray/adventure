import java.util.ArrayList;
import java.util.Arrays;

/**
 * . Class containing various sample objects, used for testing.
 *
 * @author diproray
 */
public class Sample {

  public static final String JSON_STRING_FROM_URL =
      "{\"startingRoom\":\"MatthewsStreet\",\"rooms\":[{\"directions\":[{\"directionName\":\"East\",\"room\":\"Siebel1314\"}],\"name\":\"MatthewsStreet\",\"description\":\"You are on Matthews, outside the Siebel Center\",\"monstersInRoom\":[\"Cookie Monster\"],\"items\":[{\"damage\":100,\"name\":\"coin\"}]},{\"directions\":[{\"directionName\":\"West\",\"room\":\"MatthewsStreet\"}],\"name\":\"Siebel1314\",\"description\":\"You are in Siebel 1314.  There are happy CS 126 students doing a code review.\",\"monstersInRoom\":[\"Loch Ness Monster\"],\"items\":[{\"damage\":300,\"name\":\"bazooka\"}]}],\"monsters\":[{\"defense\":150,\"attack\":250,\"name\":\"Cookie Monster\",\"health\":750},{\"defense\":450,\"attack\":200,\"name\":\"Loch Ness Monster\",\"health\":750}],\"endingRoom\":\"Siebel1314\",\"player\":{\"defense\":450,\"level\":1,\"attack\":350,\"name\":\"Dipro Ray\",\"health\":1000,\"items\":[]}}";
  public static final String SAMPLE_TESTING_JSON_STRING =
      "{\n"
          + "  \"startingRoom\":\"MatthewsStreet\",\n"
          + "  \"endingRoom\":\"Siebel1314\",\n"
          + "  \"rooms\":[\n"
          + "    {\n"
          + "      \"name\":\"MatthewsStreet\",\n"
          + "      \"description\":\"You are on Matthews, outside the Siebel Center\",\n"
          + "      \"directions\":[\n"
          + "        {\n"
          + "          \"directionName\":\"East\",\n"
          + "          \"room\":\"Siebel1314\"\n"
          + "        }\n"
          + "      ],\n"
          + "      \"items\":[\n"
          + "        {\n"
          + "          \"name\":\"coin\",\n"
          + "          \"damage\":100\n"
          + "        }\n"
          + "      ],\n"
          + "      \"monstersInRoom\":[\n"
          + "        \"Cookie Monster\"\n"
          + "      ]\n"
          + "    },\n"
          + "    {\n"
          + "      \"name\":\"Siebel1314\",\n"
          + "      \"description\":\"You are in Siebel 1314.  There are happy CS 126 students doing a code review.\",\n"
          + "      \"directions\":[\n"
          + "        {\n"
          + "          \"directionName\":\"West\",\n"
          + "          \"room\":\"MatthewsStreet\"\n"
          + "        }\n"
          + "      ],\n"
          + "      \"items\":[\n"
          + "        {\n"
          + "          \"name\":\"bazooka\",\n"
          + "          \"damage\":300\n"
          + "        }\n"
          + "      ],\n"
          + "      \"monstersInRoom\":[\n"
          + "        \"Loch Ness Monster\"\n"
          + "      ]\n"
          + "    }\n"
          + "  ],\n"
          + "  \"player\":\n"
          + "  {\n"
          + "    \"name\":\"Dipro Ray\",\n"
          + "    \"items\":[\n"
          + "\n"
          + "    ],\n"
          + "    \"attack\":350,\n"
          + "    \"defense\":450,\n"
          + "    \"health\":1000,\n"
          + "    \"level\":1\n"
          + "  },\n"
          + "  \"monsters\":[\n"
          + "    {\n"
          + "      \"name\":\"Cookie Monster\",\n"
          + "      \"attack\":250,\n"
          + "      \"defense\":150,\n"
          + "      \"health\":750\n"
          + "    },\n"
          + "    {\n"
          + "      \"name\":\"Loch Ness Monster\",\n"
          + "      \"attack\":200,\n"
          + "      \"defense\":450,\n"
          + "      \"health\":750\n"
          + "    }\n"
          + "  ]\n"
          + "}";

  public static final Direction DIRECTION_ONE = new Direction("East", "Siebel1314");
  public static final Direction DIRECTION_TWO = new Direction("West", "MatthewsStreet");

  public static final Item ITEM_ONE = new Item("coin", 100);
  public static final Item ITEM_TWO = new Item("bazooka", 300);
  public static final Item ITEM_THREE = new Item("pencil", 200);

  public static final Monster MONSTER_ONE = new Monster("Cookie Monster", 250, 150, 750);
  public static final Monster MONSTER_TWO = new Monster("Loch Ness Monster", 200, 450, 750);

  public static final Player PLAYER_ONE =
      new Player("Dipro Ray", new ArrayList<Item>(), 350, 450, 1000, 1);

  public static final Player PLAYER_TWO =
      new Player(
          "Dipro Ray",
          new ArrayList<Item>(Arrays.asList(ITEM_ONE, ITEM_TWO, ITEM_THREE)),
          350,
          450,
          1000,
          1);
  public static final Room ROOM_ONE =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {DIRECTION_ONE},
          new ArrayList<Item>(Arrays.asList(ITEM_ONE)),
          new ArrayList<String>(Arrays.asList("Cookie Monster")));

  public static final Room ROOM_TWO =
      new Room(
          "Siebel1314",
          "You are in Siebel 1314.  There are happy CS 126 students doing a code review.",
          new Direction[] {DIRECTION_TWO},
          new ArrayList<Item>(Arrays.asList(ITEM_TWO)),
          new ArrayList<String>(Arrays.asList("Loch Ness Monster")));

  public static final Layout SAMPLE_LAYOUT =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_ONE, ROOM_TWO},
          PLAYER_ONE,
          new Monster[] {MONSTER_ONE, MONSTER_TWO});

  public static final Layout SAMPLE_LAYOUT_THREE =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_ONE, ROOM_TWO},
          new Player("Dipro Ray", new ArrayList<Item>(Arrays.asList(ITEM_ONE)), 350, 450, 1000, 1),
          new Monster[] {MONSTER_ONE, MONSTER_TWO});

  public static final Layout SAMPLE_LAYOUT_FOUR =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_ONE, ROOM_TWO},
          new Player("Dipro Ray", new ArrayList<Item>(Arrays.asList(ITEM_ONE)), 350, 450, 1000, 1),
          new Monster[] {MONSTER_ONE, MONSTER_TWO});

  public static final Room ROOM_THREE =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {DIRECTION_ONE},
          new ArrayList<Item>(Arrays.asList(ITEM_ONE)),
          new ArrayList<String>());

  public static final Room ROOM_FOUR =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {DIRECTION_ONE, DIRECTION_TWO},
          new ArrayList<Item>(Arrays.asList(ITEM_ONE, ITEM_THREE)),
          new ArrayList<String>());

  public static final Room ROOM_FIVE =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {},
          new ArrayList<Item>(),
          new ArrayList<String>());

  public static final Room ROOM_SIX =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {DIRECTION_ONE, DIRECTION_TWO, DIRECTION_ONE},
          new ArrayList<Item>(),
          new ArrayList<String>());

  public static final Layout SAMPLE_LAYOUT_TWO =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_THREE, ROOM_TWO},
          PLAYER_ONE,
          new Monster[] {MONSTER_ONE, MONSTER_TWO});

  public static final String INFO_OF_ROOM_ONE =
      "You are on Matthews, outside the Siebel Center\n"
          + "Your journey begins here\n"
          + "This room contains coin\n"
          + "From here, you can go: East";

  public static final String INFO_OF_ROOM_TWO =
      "You are in Siebel 1314.  There are happy CS 126 students doing a code review.\n"
          + "You have reached your final destination\n"
          + "This room contains bazooka\n"
          + "From here, you can go: West";

  public static final Game SAMPLE_GAME = new Game(ROOM_ONE, SAMPLE_LAYOUT, 1000.0);

  public static final Game SAMPLE_GAME_TWO = new Game(ROOM_THREE, SAMPLE_LAYOUT, 1000);

  public static final Game SAMPLE_GAME_THREE = new Game(ROOM_ONE, SAMPLE_LAYOUT_THREE, 1000);

  public static final Game SAMPLE_GAME_FOUR = new Game(ROOM_FOUR, SAMPLE_LAYOUT, 1000.0);

  public static final Game SAMPLE_GAME_FIVE = new Game(ROOM_ONE, SAMPLE_LAYOUT_FOUR, 1000);

  public static final Game SAMPLE_GAME_SIX = new Game(ROOM_TWO, SAMPLE_LAYOUT, 1000);

  public static final ArrayList<String> ARRAY_LIST =
      new ArrayList<String>(Arrays.asList("Macbook", "USB-C Adapter"));

  public static final String STRING_OF_LIST = "You are carrying Macbook, USB-C Adapter\n";

  public static final Direction DIRECTION_ONE_READ_ONLY = new Direction("East", "Siebel1314");
  public static final Direction DIRECTION_TWO_READ_ONLY = new Direction("West", "MatthewsStreet");

  public static final Item ITEM_ONE_READ_ONLY = new Item("coin", 100);
  public static final Item ITEM_TWO_READ_ONLY = new Item("bazooka", 300);
  public static final Item ITEM_THREE_READ_ONLY = new Item("pencil", 200);

  public static final Monster MONSTER_ONE_READ_ONLY = new Monster("Cookie Monster", 250, 150, 750);
  public static final Monster MONSTER_TWO_READ_ONLY =
      new Monster("Loch Ness Monster", 200, 450, 750);

  public static final Player PLAYER_ONE_READ_ONLY =
      new Player("Dipro Ray", new ArrayList<Item>(), 350, 450, 1000, 1);

  public static final Room ROOM_ONE_READ_ONLY =
      new Room(
          "MatthewsStreet",
          "You are on Matthews, outside the Siebel Center",
          new Direction[] {DIRECTION_ONE},
          new ArrayList<Item>(Arrays.asList(ITEM_ONE)),
          new ArrayList<String>(Arrays.asList("Cookie Monster")));

  public static final Room ROOM_TWO_READ_ONLY =
      new Room(
          "Siebel1314",
          "You are in Siebel 1314.  There are happy CS 126 students doing a code review.",
          new Direction[] {DIRECTION_TWO},
          new ArrayList<Item>(Arrays.asList(ITEM_TWO)),
          new ArrayList<String>(Arrays.asList("Loch Ness Monster")));

  public static final Layout SAMPLE_LAYOUT_READ_ONLY =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_ONE_READ_ONLY, ROOM_TWO_READ_ONLY},
          PLAYER_ONE_READ_ONLY,
          new Monster[] {MONSTER_ONE_READ_ONLY, MONSTER_TWO_READ_ONLY});

  public static final Layout SAMPLE_LAYOUT_READ_ONLY_2 =
      new Layout(
          "MatthewsStreet",
          "Siebel1314",
          new Room[] {ROOM_ONE_READ_ONLY, ROOM_TWO_READ_ONLY},
          PLAYER_TWO,
          new Monster[] {MONSTER_ONE_READ_ONLY, MONSTER_TWO_READ_ONLY});

  public static final Game SAMPLE_GAME_READ_ONLY =
      new Game(ROOM_ONE_READ_ONLY, SAMPLE_LAYOUT_READ_ONLY, 1000.0);

  public static final Game SAMPLE_GAME_READ_ONLY_TWO =
      new Game(ROOM_ONE_READ_ONLY, SAMPLE_LAYOUT_READ_ONLY_2, 1000.0);

  public static final Monster MONSTER_ONE_CHANGING = new Monster("Cookie Monster", 250, 150, 750);

  public static final Monster MONSTER_ONE_COPY_CHANGING =
      new Monster("Cookie Monster", 250, 150, 750);

  public static final Monster MONSTER_ONE_NEGATIVE_COPY_CHANGING =
      new Monster("Cookie Monster", 250, 150, 100);
}
