package site.hiddengames.practice.exercism.dndcharacter;

import site.hiddengames.annotations.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * For a game of Dungeons & Dragons, each player starts by generating a character they can play with. <br>
 * This character has, among other things, six abilities; strength, dexterity, constitution, intelligence, wisdom and charisma. <br>
 * These six abilities have scores that are determined randomly. <br>
 * You do this by rolling four 6-sided dice and record the sum of the largest three dice. <br>
 * You do this six times, once for each ability. <br><br>
 *
 * Your character's initial hitpoints are 10 + your character's constitution modifier. <br>
 * You find your character's constitution modifier by subtracting 10 from your character's constitution, divide by 2 and round down. <br><br>
 *
 * For example, the six throws of four dice may look like:<br><br>
 *
 * 5, 3, 1, 6: You discard the 1 and sum 5 + 3 + 6 = 14, which you assign to strength.<br>
 * 3, 2, 5, 3: You discard the 2 and sum 3 + 5 + 3 = 11, which you assign to dexterity.<br>
 * 1, 1, 1, 1: You discard the 1 and sum 1 + 1 + 1 = 3, which you assign to constitution.<br>
 * 2, 1, 6, 6: You discard the 1 and sum 2 + 6 + 6 = 14, which you assign to intelligence.<br>
 * 3, 5, 3, 4: You discard the 3 and sum 5 + 3 + 4 = 12, which you assign to wisdom.<br>
 * 6, 6, 6, 6: You discard the 6 and sum 6 + 6 + 6 = 18, which you assign to charisma.<br>
 * Because constitution is 3, the constitution modifier is -4 and the hitpoints are 6.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "DnDCharacterTest" })
class DnDCharacter {
    Map<String, Integer> abilities = new HashMap<>();

    int ability() {
        // Using ThreadLocalRandom to generate random numbers between 1 and 6 since nextInt() is exclusive bound
        // ThreadLocalRandom is the thread-safe version of Random that allows scaling this program across multiple threads
        // limit(4) -> Generate 4 numbers
        // sorted() -> Sort the numbers from smallest to largest
        // skip(1) -> Skip the first number since we know it is now the smallest
        // boxed() -> Convert from int to Integer
        // reduce() -> Add all values of the stream to provide the total sum.
        return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(1, 7))
                .limit(4)
                .sorted()
                .skip(1)
                .boxed()
                .reduce(0, Integer::sum);
    }

    int modifier(int input) {
        double modifier = Math.floor((input - 10) /2.0);
        return (int) modifier;
    }

    int getStrength() { return abilities.computeIfAbsent("strength", s -> ability()); }
    int getDexterity() { return abilities.computeIfAbsent("dexterity", s -> ability()); }
    int getConstitution() { return abilities.computeIfAbsent("constitution", s -> ability()); }
    int getIntelligence() { return abilities.computeIfAbsent("intelligence", s -> ability()); }
    int getWisdom() { return abilities.computeIfAbsent("wisdom", s -> ability()); }
    int getCharisma() { return abilities.computeIfAbsent("charisma", s -> ability()); }
    int getHitpoints() { return 10 + modifier(getConstitution()); }
}
