package adeo.leroymerlin.cdp.domain;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class GenericConditionalUpdateField<T> {

    protected void conditionalUpdateField(T newEntity, Predicate<T> condition, Consumer<T> action) {
        Optional.ofNullable(newEntity)
                .filter(condition)
                .ifPresent(action);
    }
}
