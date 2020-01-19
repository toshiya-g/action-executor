package com.github.toshiyag.actionexec;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExceptionHandlerPool<E extends Exception> {

    private final Map<String, ExceptionHandleable<E>> pool = new HashMap<>();

    public ExceptionHandlerPool(ExceptionHandleable<E> handler) {
        this.pool.put(handler.getTargetExceptionClass().getName(), handler);
    }

    public ExceptionHandlerPool(Collection<ExceptionHandleable<E>> handlers) {
        this.pool.putAll(this.createMapBy(handlers));
    }

    public void addHandler(ExceptionHandleable<E> handler) {
        this.pool.put(handler.getTargetExceptionClass().getName(), handler);
    }

    public void addHandler(Collection<ExceptionHandleable<E>> handlers) {
        this.pool.putAll(this.createMapBy(handlers));
    }

    public Optional<ExceptionHandleable<E>> getMatchedHandler(Exception e) {
        return this.pool.containsKey(e.getClass().getName())
            ? Optional.ofNullable(this.pool.get(e.getClass().getName()))
            : Optional.empty();
    }

    private Map<String, ExceptionHandleable<E>> createMapBy(Collection<ExceptionHandleable<E>> handlers) {
        return handlers.stream().collect(Collectors.toMap(h -> h.getTargetExceptionClass().getName(), Function.identity()));
    }
}
