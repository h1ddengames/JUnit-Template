package annotations;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface API {
    API.Status status();
    String since() default "";
    String[] consumers() default {"*"};

    public static enum Status {
        INTERNAL,
        DEPRECATED,
        EXPERIMENTAL,
        MAINTAINED,
        STABLE;

        private Status() { }
    }
}