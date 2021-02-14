package site.hiddengames.practice.exercism.leap;

import site.hiddengames.annotations.API;
import site.hiddengames.annotations.ClassMetadata;

/**
 * Given a year, report if it is a leap year. <br>
 * The tricky thing here is that a leap year in the Gregorian calendar occurs: <br>
 * - on every year that is evenly divisible by 4 <br>
 *   - except every year that is evenly divisible by 100 <br>
 *     - unless the year is also evenly divisible by 400 <br> <br>
 * For example, 1997 is not a leap year, but 1996 is. 1900 is not a leap year, but 2000 is.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "2/13/2021",
        currentRevision = 1, lastModified = "2/13/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v3.0.0", consumers = { "LeapTest" })
public class Leap {
    boolean isLeapYear(int year) {
        if(year % 4 == 0) {
            return year % 100 != 0 || year % 400 == 0;
        }

        return false;
    }
}
