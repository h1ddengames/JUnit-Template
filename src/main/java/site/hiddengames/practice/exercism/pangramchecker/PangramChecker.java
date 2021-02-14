package site.hiddengames.practice.exercism.pangramchecker;

import site.hiddengames.annotations.*;
import java.util.stream.Collectors;

/**
 * Determine if a sentence is a pangram. <br>
 * A pangram (Greek: παν γράμμα, pan gramma, "every letter")
 * is a sentence using every letter of the alphabet at least once. <br>
 * The best known English pangram is: The quick brown fox jumps over the lazy dog.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "PangramCheckerTest" })
public class PangramChecker {
    public boolean isPangram(String input) {
        return input.toLowerCase().chars()
                .filter(Character::isAlphabetic)
                .boxed()
                .collect(Collectors.toSet()).size() == 26;
    }
}
