package org.example.interfaces;
import java.lang.annotation.*;
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ReadMarkedField {
}
