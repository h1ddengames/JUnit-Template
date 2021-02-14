package site.hiddengames.practice.exercism.resistorcolorduo;

import site.hiddengames.annotations.*;

/**
 * Each resistor has a resistance value. <br>
 * Resistors are small - so each band has a position and a numeric value. <br>
 * For example, if they printed a brown band (value 1) followed by a green band (value 5), <br>
 * it would translate to the number 15. <br><br>
 * From the example above: brown-green should return 15 brown-green-violet should return 15 too, ignoring the third color.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "ResistorColorDuoTest" })
public class ResistorColorDuo {
    enum Colors {
        BLACK("BLACK", 0),
        BROWN("BROWN", 1),
        RED("RED", 2),
        ORANGE("ORANGE",3),
        YELLOW("YELLOW", 4),
        GREEN("GREEN", 5),
        BLUE("BLUE", 6),
        VIOLET("VIOLET", 7),
        GREY("GREY", 8),
        WHITE("WHITE", 9);

        private final int value;
        private String id;

        Colors(String id, int value) {
            this.id = id;
            this.value = value;
        }

        public int getValue() { return value; }
        public String getId() { return id; }

        public static Colors fromString(String text) {
            for (Colors color : Colors.values()) {
                if (color.getId().equalsIgnoreCase(text)) {
                    return color;
                }
            }
            throw new IllegalArgumentException("Given string does not match any known color in this enum.");
        }
    }

    int value(String[] colors) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < colors.length; i++) {
            if(i == 2) { break; }
            stringBuilder.append(Colors.fromString(colors[i]).getValue());
        }

        return Integer.parseInt(stringBuilder.toString());
    }
}

