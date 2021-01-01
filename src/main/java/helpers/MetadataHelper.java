package helpers;

import annotations.API;
import annotations.ClassMetadata;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A helper class that provides metadata such as method name, class name, date and time.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/31/2020",
        currentRevision = 1, lastModified = "12/31/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class MetadataHelper {
    public enum DateFormat { US, OUS }
    public enum TimeFormat { CIVILIAN, MILITARY }

    /**
     * Get the method name of whichever method calls this method.
     * @return The method name calling this method.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    /**
     * Get the class name of whichever class's method is calling this method.
     * @return The class name of the method calling this method.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }

    /**
     * Get the current date with a given format:
     * <p>format: US = "MM/dd/yyyy" ex: "07/12/1999</p>
     * format: OUS = "dd/MM/yyyy" ex: 12/07/1999
     * @param dateFormat The formatting the date should have: US or OUS formatting.
     * @return A date formatted with the given date format.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getDate(DateFormat dateFormat) {
        SimpleDateFormat formatter;
        Date date = new Date();

        switch (dateFormat) {
            case US:
                formatter = new SimpleDateFormat("MM/dd/yyyy");
                return formatter.format(date);
            case OUS:
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                return formatter.format(date);
            default:
                return "ERROR: EMPTY STRING FROM MetadataHelper.getTime";
        }
    }

    /**
     * Get the current time with a given format:
     * <p>format: CIVILIAN = "hh:mm:ss aa" ex: 11:52:22 PM</p>
     * format: MILITARY = "dd/MM/yyyy" ex: 23:52:22
     * @param timeFormat The formatting the time should have: CIVILIAN or MILITARY formatting.
     * @return A time formatted with the given time format.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getTime(TimeFormat timeFormat) {
        SimpleDateFormat formatter;
        Date date = new Date();

        switch (timeFormat) {
            case CIVILIAN:
                formatter = new SimpleDateFormat("hh:mm:ss aa");
                return formatter.format(date);
            case MILITARY:
                formatter = new SimpleDateFormat("HH:mm:ss");
                return formatter.format(date);
            default:
                return "ERROR: EMPTY STRING FROM MetadataHelper.getTime";
        }
    }
}
