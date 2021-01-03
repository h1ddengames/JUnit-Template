package annotations;

import java.lang.annotation.*;

/**
 * Provides a metadata information for all methods to display in the javadocs.
 * <p>Example usage:
 * <pre>
 * <code>
 *     {@literal @}API(status = API.Status.STABLE, since = "Project-Template-v2.0.0", consumers = { "AppUnitTests", "AppIntegrationTests"})
 * </code>
 * </pre>
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface API {
    Status status();
    String since() default "";
    String[] consumers() default {"*"};

    enum Status {
        INTERNAL,
        DEPRECATED,
        EXPERIMENTAL,
        MAINTAINED,
        STABLE;

        Status() { }
    }
}