package helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DataGeneration {
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

    public static final List<String> stateCodes = Arrays.asList(
        ("AL, AK, AZ, AR, CA, CO, CT, DE, FL, GA, " +
        "HI, ID, IL, IN, IA, KS, KY, LA, ME, MD, " +
        "MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, " +
        "NM, NY, NC, ND, OH, OK, OR, PA, RI, SC, " +
        "SD, TN, TX, UT, VT, VA, WA, WV, WI, WY").split(", ")
    );

    public static final List<String> stateNames = Arrays.asList(
        ("Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware, Florida, Georgia, " +
        "Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana, Maine, Maryland, " +
        "Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana, Nebraska, Nevada, New Hampshire, New Jersey, " +
        "New Mexico, New York, North Carolina, North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina, " +
        "South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia, Wisconsin, Wyoming").split(", ")
    );

    public static final List<String> femaleFirstNames = Arrays.asList(
        "Mary", "Patricia", "Linda", "Barbara", "Elizabeth",
        "Jennifer", "Maria", "Susan", "Margaret", "Dorothy", "Lisa",
        "Nancy", "Karen", "Betty", "Helen", "Sandra", "Donna", "Carol",
        "Ruth", "Sharon", "Michelle", "Laura", "Sarah", "Cynthia", "Jessica"
    );

    public static final List<String> maleFirstNames = Arrays.asList(
        "James", "John", "Robert", "Michael", "William",
        "David", "Richard", "Charles", "Joseph", "Thomas", "Christopher",
        "Daniel", "Paul", "Mark", "George", "Kenneth", "Steven", "Edward",
        "Brian", "Ron", "Anthony", "Kevin", "Jason", "Matthew", "Gary"
    );

    public static final List<String> lastNames = Arrays.asList(
        "Smith", "Johnson", "Williams", "Jones", "Brown",
        "Black", "White", "Davis", "Miller", "Wilson", "Moore",
        "Taylor", "Anderson", "Thomas", "Jackson", "Harris", "Martin", "Thompson",
        "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee"
    );

    public static int getRandomInt() { return ThreadLocalRandom.current().nextInt(); }
    public static int getRandomInt(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max); }
    public static long getRandomLong() { return ThreadLocalRandom.current().nextLong(); }
    public static long getRandomLong(long min, long max) { return ThreadLocalRandom.current().nextLong(min, max); }
    public static double getRandomDouble() { return ThreadLocalRandom.current().nextDouble(); }
    public static double getRandomDouble(double min, double max) { return ThreadLocalRandom.current().nextDouble(min, max); }
    public static float getRandomFloat() { return ThreadLocalRandom.current().nextFloat(); }
    public static boolean getRandomBoolean() { return ThreadLocalRandom.current().nextBoolean(); }

    public static <T> T getRandomValueFrom(List<T> items) { return items.get(getRandomInt(0, items.size())); }
    public static <T> T getRandomValueFrom(T[] items) { return items[getRandomInt(0, items.length)]; }

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

    public static String getRandomAlphaString(int length) {
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return getRandomString(alphaString, length);
    }

    public static String getRandomAlphaNumericString(int length) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return getRandomString(alphaNumericString, length);
    }

    public static String getRandomNumericString(int length) {
        String numericString = "0123456789";
        return getRandomString(numericString, length);
    }

    static String generateFemaleFullName() {
        return getRandomValueFrom(femaleFirstNames) + " " + getRandomValueFrom(lastNames);
    }

    static String generateMaleFullName() {
        return getRandomValueFrom(maleFirstNames) + " " + getRandomValueFrom(lastNames);
    }
}