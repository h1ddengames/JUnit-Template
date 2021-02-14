package site.hiddengames.annotations;

import java.lang.annotation.*;

/**
 * Provides a metadata information for all classes to display in the javadocs.
 * <p>Example usage:
 * <pre>
 * <code>
 * {@literal @}ClassMetadata(
 *         author = "Shahid Karim", dateCreated = "11/12/2020",
 *         currentRevision = 2, lastModified = "11/14/2020", lastModifiedBy = "Shahid Karim",
 *         reviewers = {}
 * )
 * </code>
 * </pre>
 */
@Documented
@Target(ElementType.TYPE)
public @interface ClassMetadata {
    String author();

    /**
     * Expected format: MM/DD/YYYY
     * @return A date formated: MM/DD/YYYY
     */
    String dateCreated();

    int currentRevision() default 1;

    /**
     * Expected format: MM/DD/YYYY
     * @return A date formated: MM/DD/YYYY
     */
    String lastModified() default "N/A";

    /**
     * Expected format: MM/DD/YYYY
     * @return A date formated: MM/DD/YYYY
     */
    String lastModifiedBy() default "N/A";

    String[] reviewers();
}