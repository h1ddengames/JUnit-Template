package site.hiddengames.practice.exercism.hamming;

import site.hiddengames.annotations.*;

/**
 * Calculate the Hamming Distance between two DNA strands. <br>
 * We compare two strands of DNA and count the differences between them we can see how many mistakes occurred. <br>
 * This is known as the "Hamming Distance". <br>
 * GAGCCTACTAACGGGAT <br>
 * CATCGTAATGACGGCCT <br>
 * ^ ^ ^  ^ ^    ^^ <br>
 * In the example above, there are 7 differences, and therefore the Hamming Distance is 7.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "HammingTest" })
public class Hamming {
    int hammingDistance = 0;

    public Hamming(String leftStrand, String rightStrand) {
        if(leftStrand.length() != rightStrand.length()) {
            if(leftStrand.isEmpty()) { throw new IllegalArgumentException("left strand must not be empty."); }
            if(rightStrand.isEmpty()) { throw new IllegalArgumentException("right strand must not be empty."); }
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }

        if(leftStrand.contentEquals(rightStrand)) { hammingDistance = 0; }

        for (int i = 0; i < leftStrand.length(); i++) {
            if(leftStrand.charAt(i) != rightStrand.charAt(i)) { hammingDistance++; }
        }
    }

    public int getHammingDistance() { return hammingDistance; }
}
