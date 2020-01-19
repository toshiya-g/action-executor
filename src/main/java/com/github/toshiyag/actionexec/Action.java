package com.github.toshiyag.actionexec;

public interface Action<T, E extends Exception> {
    T action() throws E;
}