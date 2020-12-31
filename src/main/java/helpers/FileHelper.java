package helpers;

import annotations.API;
import annotations.ClassMetadata;

import java.io.File;
import java.util.Optional;

/**
 * A helper class that provides methods that generate data.
 */
@ClassMetadata(
        author = "Shahid Karim", dateCreated = "12/29/2020",
        currentRevision = 2, lastModified = "12/30/2020", lastModifiedBy = "Shahid Karim",
        reviewers = {}
)
@API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
public class FileHelper {

    /**
     * Get the file type from the filename.
     * @param fileName The file as a String to get the file type from.
     * @return An Optional String containing the file type if found, if not an Optional empty will be returned.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static Optional<String> findExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return Optional.empty();
        }
        return Optional.of(fileName.substring(lastIndex + 1));
    }

    /**
     * Get the file type from the filename.
     * @param file The file to get the file type from.
     * @return An Optional String containing the file type if found, if not an Optional empty will be returned.
     */
    @API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = {})
    public static Optional<String> findExtension(File file) {
        String fileName = file.toString();
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return Optional.empty();
        }
        return Optional.of(fileName.substring(lastIndex + 1));
    }
}
