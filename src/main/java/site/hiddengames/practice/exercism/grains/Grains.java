package site.hiddengames.practice.exercism.grains;

import site.hiddengames.annotations.API;
import site.hiddengames.annotations.ClassMetadata;

import java.math.BigInteger;

/**
 * Calculate the number of grains of wheat on a chessboard given that the number on each square doubles. <br>
 * There are 64 squares on a chessboard (where square 1 has one grain, square 2 has two grains, and so on).
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "GrainsTest" })
class Grains {
    BigInteger grainsOnSquare(final int square) {
        if(square <= 0 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }

        return BigInteger.valueOf(2).pow(square - 1);
    }

    BigInteger grainsOnBoard() {
        BigInteger total = new BigInteger("0");

        for (int i = 1; i < 65; i++) {
            total = total.add(grainsOnSquare(i));
        }

        return total;
    }
}