package site.hiddengames.practice.exercism.armstrongnumbers;

import site.hiddengames.annotations.*;

/**
 * An Armstrong number is a number that is the sum of its own digits each raised to the power of the number of digits. <br><br>
 *
 * For example: <br>
 *
 * 9 is an Armstrong number, because 9 = 9^1 = 9 <br>
 * 10 is not an Armstrong number, because 10 != 1^2 + 0^2 = 1 <br>
 * 153 is an Armstrong number, because: 153 = 1^3 + 5^3 + 3^3 = 1 + 125 + 27 = 153 <br>
 * 154 is not an Armstrong number, because: 154 != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 <br>
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "ArmstrongNumbersTest" })
class ArmstrongNumbers {
    boolean isArmstrongNumber(int numberToCheck) {
        char[] digitsAsChars = String.valueOf(numberToCheck).toCharArray();
        int total = 0;

        for (char digitsAsChar : digitsAsChars) {
            total += Math.pow(Integer.parseInt(String.valueOf(digitsAsChar)), digitsAsChars.length);
        }

        return total == numberToCheck;
    }
}
