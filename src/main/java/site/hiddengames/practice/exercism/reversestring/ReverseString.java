package site.hiddengames.practice.exercism.reversestring;

import site.hiddengames.annotations.*;

/**
 * Reverse a string.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "ReverseStringTest" })
public class ReverseString {
    String reverse(String inputString) {
        if(inputString == null || inputString.contentEquals("")) { return ""; }

        StringBuilder reversedString = new StringBuilder();

        for (int i = inputString.length() - 1; i >= 0; i--) {
            reversedString.append(inputString.charAt(i));
        }

        return reversedString.toString();
    }
}