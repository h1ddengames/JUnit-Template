package annotations;

import java.lang.annotation.*;

/**
 * Provides a metadata information for all classes to display in the javadocs.
 */
@Documented
@Target(ElementType.TYPE)
public @interface ClassMetadata {
    String author();
    String dateCreated();
    int currentRevision() default 1;
    String lastModified() default "N/A";
    String lastModifiedBy() default "N/A";
    String[] reviewers();
}