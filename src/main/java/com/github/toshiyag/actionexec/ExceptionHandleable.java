package com.github.toshiyag.actionexec;

public interface ExceptionHandleable<E extends Exception> {
    void handle(E e);
    Class<E> getTargetExceptionClass();
}
