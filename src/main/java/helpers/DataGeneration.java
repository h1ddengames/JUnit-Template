package helpers;

import annotations.API;
import annotations.ClassMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A helper class that provides methods that generate data.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class DataGeneration {
    /**
     * A list containing 88 two character country codes.
     */
    public static final List<String> countryCodes = Arrays.asList(
        ("DZ, EG, MU, YT, MA, ZA, TN, CA, CR, MX, " +
        "PA, PR, US, AR, BO, BR, CL, CO, EC, PE, " +
        "UY, VE, AM, BH, HK, IN, ID, IL, JP, KW, " +
        "LB, MY, MV, OM, PK, PH, QA, SA, SG, KR, " +
        "TW, TH, TR, AE, VN, AL, AT, BE, BA, BG, " +
        "HR, CY, CZ, DK, EE, FI, FR, GE, DE, GR, " +
        "HU, IS, IE, IT, XK, LV, LT, LU, MK, MT, " +
        "ME, NL, NO, PL, PT, RO, RU, RS, SK, SI, " +
        "ES, SE, CH, UA, GB, AU, NC, NZ").split(", "));

    /**
     * A list containing 88 country names matching the indices of countryCodes list.
     */
    public static final List<String> countryNames = Arrays.asList(
        ("Algeria, Egypt, Mauritius, Mayotte, Morocco, South Africa, Tunisia, Canada, " +
        "Costa Rica, Mexico, Panama, Puerto Rico, United States, Argentina, Bolivia, " +
        "Brazil, Chile, Colombia, Ecuador, Peru, Uruguay, Venezuela, Armenia, Bahrain, " +
        "Hong Kong, India, Indonesia, Israel, Japan, Kuwait, Lebanon, Malaysia, Maldives, " +
        "Oman, Pakistan, Philippines, Qatar, Saudi Arabia, Singapore, South Korea, Taiwan, " +
        "Thailand, Turkey, United Arab Emirates, Vietnam, Albania, Austria, Belgium, " +
        "Bosnia and Herzegovina, Bulgaria, Croatia, Cyprus, Czech Republic, Denmark, " +
        "Estonia, Finland, France, Georgia, Germany, Greece, Hungary, Iceland, Ireland, " +
        "Italy, Kosovo, Latvia, Lithuania, Luxembourg, Macedonia, Malta, Montenegro, " +
        "Netherlands, Norway, Poland, Portugal, Romania, Russia, Serbia, Slovakia, " +
        "Slovenia, Spain, Sweden, Switzerland, Ukraine, United Kingdom, Australia, " +
        "New Caledonia, New Zealand").split(", ")
    );

    /**
     * A list containing the 50 state names belonging to the United States of America.
     */
    public static final List<String> stateCodes = Arrays.asList(
        ("AL, AK, AZ, AR, CA, CO, CT, DE, FL, GA, " +
        "HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, " +
        "MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, " +
        "NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, " +
        "SD, TN, TX, UT, VT, VA, WA, WV, WI, WY").split(", ")
    );

    /**
     * A list containing the 50 two character state codes belonging to the United States of America matching the indices of stateCodes list.
     */
    public static final List<String> stateNames = Arrays.asList(
        ("Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, " +
        "Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, " +
        "Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, " +
        "New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, " +
        "South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming").split(", ")
    );

    /**
     * A list containing female first names.
     */
    public static final List<String> femaleFirstNames = Arrays.asList(
        "Mary", "Patricia", "Linda", "Barbara", "Elizabeth",
        "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa",
        "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol",
        "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Cynthia", "Jessica"
    );

    /**
     * A list containing male first names.
     */
    public static final List<String> maleFirstNames = Arrays.asList(
        "James", "John", "Robert", "Michael", "William",
        "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher",
        "Daniel", "Paul", "Mark", "George", "Kenneth", "Steven", "Edward",
        "Brian", "Ron", "Anthony", "Kevin", "Jason", "Matthew", "Gary"
    );

    /**
     * A list containing last names.
     */
    public static final List<String> lastNames = Arrays.asList(
        "Smith", "Johnson", "Williams", "Jones", "Brown",
        "Black", "White", "Davis", "Miller", "Wilson", "Moore",
        "Taylor", "Anderson", "Thomas", "Jackson", "Harris", "Martin", "Thompson",
        "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee"
    );

    /**
     * Returns a random int.
     * @return A random int.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static int getRandomInt() { return ThreadLocalRandom.current().nextInt(); }

    /**
     * Returns a random int between a min and max int.
     * @param min The minimum the int can be (inclusive)
     * @param max The maximum the int can be (exclusive)
     * @return A random int between the given min and max values.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static int getRandomInt(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max); }

    /**
     * Returns a random long.
     * @return A random long.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static long getRandomLong() { return ThreadLocalRandom.current().nextLong(); }

    /**
     * Returns a random long between a min and max long.
     * @param min The minimum the long can be (inclusive)
     * @param max The maximum the long can be (exclusive)
     * @return A random long between the given min and max values.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static long getRandomLong(long min, long max) { return ThreadLocalRandom.current().nextLong(min, max); }

    /**
     * Returns a random double.
     * @return A random double.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static double getRandomDouble() { return ThreadLocalRandom.current().nextDouble(); }

    /**
     * Returns a random double between a min and max double.
     * @param min The minimum the double can be (inclusive)
     * @param max The maximum the double can be (exclusive)
     * @return A random double between the given min and max values.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static double getRandomDouble(double min, double max) { return ThreadLocalRandom.current().nextDouble(min, max); }

    /**
     * Returns a random float.
     * @return A random float.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static float getRandomFloat() { return ThreadLocalRandom.current().nextFloat(); }

    /**
     * Returns true or false randomly.
     * @return True or false as a boolean.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static boolean getRandomBoolean() { return ThreadLocalRandom.current().nextBoolean(); }

    /**
     * Get a random element from a given List.
     * @param items A List.
     * @param <T> Any type that Lists support.
     * @return A random element from the input List.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static <T> T getRandomValueFrom(List<T> items) { return items.get(getRandomInt(0, items.size())); }

    /**
     * Get a random element from a given Array.
     * @param items An Array.
     * @param <T> Any type that Arrays support.
     * @return A random element from the input Array.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static <T> T getRandomValueFrom(T[] items) { return items[getRandomInt(0, items.length)]; }

    /**
     * Given a list, random elements are added to another list until the size of the new list equals the specified numberOfItems.
     * @param items The list to get random elements from.
     * @param numberOfItems The amount of elements that should be in the new list.
     * @param <T> Any type that Lists support.
     * @return A list containing random elements from the input List.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static <T> ArrayList<T> getRandomValuesFrom(List<T> items, int numberOfItems) {
        ArrayList<T> list = new ArrayList<T>();

        for (int i = 0; i < numberOfItems; i++) { list.add(getRandomValueFrom(items)); }

        return list;
    }

    private static String getRandomString(String string, int length) {
        if(length <= 0) { return null; }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(string.charAt(getRandomInt(0, string.length())));
        }

        return stringBuilder.toString();
    }

    /**
     * Generates a random mix of alphabetical characters by appending a random character to a String.
     * @param length The length the random String should be.
     * @return A String containing a mix of randomly selected alphabetical characters.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getRandomAlphaString(int length) {
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getRandomString(alphaString, length);
    }

    /**
     * Generate a random alpha numeric String by appending a random single character to a String.
     * @param length The length the random String should be.
     * @return A String containing a mix of randomly selected alpha numeric characters.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getRandomAlphaNumericString(int length) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getRandomString(alphaNumericString, length);
    }

    /**
     * Generates a random number as a String by appending a random single digit to a String.
     * @param length The length the random String should be.
     * @return A String containing a randomly generated number of the given length.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static String getRandomNumericString(int length) {
        String numericString = "0123456789";
        return getRandomString(numericString, length);
    }

    /**
     * Generates a full name using a female first name and a random last name.
     * @return A female first name + a last name as a String.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    static String generateFemaleFullName() {
        return getRandomValueFrom(femaleFirstNames) + " " + getRandomValueFrom(lastNames);
    }

    /**
     * Generates a full name using a male first name and a random last name.
     * @return A male first name + a last name as a String.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    static String generateMaleFullName() {
        return getRandomValueFrom(maleFirstNames) + " " + getRandomValueFrom(lastNames);
    }
}