# Backup of HTMLChangerApp Source Code

Purpose - Updates a specifically formatted HTML file to contain links to newly created directories.

Usage - ``java -jar HTMLChanger-1.0.0.jar ../.website/javadocs/index.html``

Example -

Replaces the following regex:

``<!-- Start of Dynamic Content -->[\w\W\s\S]*<!-- End of Dynamic Content -->``

Equal to the following content:
```
<!-- Start of Dynamic Content -->
<div class="box">
    <a href="..." class="image fit"><img src="../images/...jpg" alt="" /></a>
    <div class="inner">
        <h3>...</h3>
        <a href="..." class="button fit">View</a>
    </div>
</div>
<!-- End of Dynamic Content -->
```

With: 

```
<!-- Start of Dynamic Content -->
<div class="box">
    <a href="01-11-2021--05:09:52PM" class="image fit"><img src="../images/img1.jpg" alt="" /></a>
    <div class="inner">
        <h3>01-11-2021--05:09:52PM</h3>
        <a href="01-11-2021--05:09:52PM" class="button fit">View</a>
    </div>
</div>
<!-- End of Dynamic Content -->
```

## App.java

```java
package entrypoint;

import annotations.*;
import helpers.DataGeneration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Below annotations can be removed without any issues.
/**
 * The entrypoint of this application.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "01/18/2021",
        currentRevision = 1, lastModified = "01/18/2021", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "HTMLChanger-1.0.0", consumers = {""})
public class App {
    static String newline = System.lineSeparator();
    static String regex = "<!-- Start of Dynamic Content -->[\\w\\W\\s\\S]*<!-- End of Dynamic Content -->";
    static Pattern htmlToChange = Pattern.compile(regex);
    static Matcher matcher;

    static String initialHTML = null;
    static String changeThisHTML = null;
    static String replaceWithThisHTML = null;

    // Replacement for call to DataGeneration.getRandomInt
    public static int getRandomInt(int min, int max) { return ThreadLocalRandom.current().nextInt(min, max); }
    
    public static void main(String[] args) {
        // Verify the amount of arguments.
        if(args.length < 1) {
            System.out.println("Too few arguments. " +
                    "The only argument should be the html file to modify.");
            return;
        }

        if(args.length > 1) {
            System.out.println("Too many arguments. " +
                    "The only argument should be the html file to modify.");
            return;
        }

        // Verify argument is HTML file.
        if(!args[0].endsWith(".html")) {
            System.out.println("Invalid file type. " +
                    "The first argument should be an HTML file.");
            return;
        }

        // Verify that the HTML file exists then read file into String.
        File file = Paths.get(args[0]).toFile();
        File directory = Paths.get(args[0]).getParent().toFile();

        if(!file.exists() || file.isDirectory()) {
            System.out.println("File not found or is a directory.");
            return;
        }

        try {
            initialHTML = Files.readString(Paths.get(args[0]));
        } catch (Exception e) {
            System.out.println("Could not read from the given file.");
            return;
        }

        matcher = htmlToChange.matcher(initialHTML);

        if(!matcher.find()) {
            System.out.println("Invalid HTML content. " +
                    "The HTML file is missing the required content to regex.");
        } else {
            changeThisHTML = matcher.group(0);
        }

        replaceWithThisHTML = buildChangedHTML(directory);

        String replacedHTML = initialHTML.replace(changeThisHTML, replaceWithThisHTML);
        try {
            Files.writeString(Paths.get(args[0]), replacedHTML, StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println("Could not write updated HTML to file.");
        }
    }

    private static String buildChangedHTML(File baseDirectory) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!-- Start of Dynamic Content -->");
        stringBuilder.append(newline);

        // Example directory: 01-11-2021-05.09.52PM
        List<File> directories =
                Arrays.stream(baseDirectory.listFiles(File::isDirectory))
                        .collect(Collectors.toList());

        //	<div class="box">
        //		<a href="01-11-2021--05:09:52PM" class="image fit"><img src="../images/img1.jpg" alt="" /></a>
        //		<div class="inner">
        //			<h3>01-11-2021--05:09:52PM</h3>
        //				<a href="01-11-2021--05:09:52PM" class="button fit">View</a>
        //		</div>
        //	</div>

        for (var directory : directories) {
            stringBuilder.append("\t\t\t\t\t")
                    .append("<div class=\"box\">")
                    .append(newline)
                    .append("\t\t\t\t\t\t")
                    .append("<a href=\"")
                    .append(directory.getName())
                    .append("\" class=\"image fit\"><img src=\"../images/img")
                    .append(DataGeneration.getRandomInt(1, 21))
                    .append(".jpg\" alt=\"\" /></a>")
                    .append(newline)
                    .append("\t\t\t\t\t\t")
                    .append("<div class=\"inner\">")
                    .append(newline)
                    .append("\t\t\t\t\t\t\t").append("<h3>")
                    .append(directory.getName())
                    .append("</h3>")
                    .append(newline)
                    .append("\t\t\t\t\t\t\t")
                    .append("<a href=\"")
                    .append(directory.getName())
                    .append("\" class=\"button fit\">View</a>")
                    .append(newline)
                    .append("\t\t\t\t\t\t")
                    .append("</div>")
                    .append(newline)
                    .append("\t\t\t\t\t")
                    .append("</div>")
                    .append(newline);
        }

        stringBuilder.append("\t\t\t\t\t\t")
                .append("<!-- End of Dynamic Content -->")
                .append(newline);

        return stringBuilder.toString();
    }
}

```