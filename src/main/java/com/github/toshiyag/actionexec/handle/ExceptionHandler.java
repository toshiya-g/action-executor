package com.github.toshiyag.actionexec.handle;

import com.github.toshiyag.actionexec.ExceptionHandleable;

import java.util.function.Consumer;

public class ExceptionHandler<E extends Exception> implements ExceptionHandleable<E> {

    private final Consumer<E> consumer;
    private final Class<E> clazz;

    public ExceptionHandler(Class<E> clazz, Consumer<E> consumer) {
        this.consumer = consumer;
        this.clazz = clazz;
    }

    @Override
    public void handle(E e) {
        consumer.accept(e);
    }

    @Override
    public Class<E> getTargetExceptionClass() {
        return this.clazz;
    }
}
