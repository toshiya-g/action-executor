package com.github.toshiyag.actionexec.executor;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<T, E extends Exception> {

    private final T succeeded;
    private final E failure;

    private Result(T t, E e) {
        this.succeeded = t;
        this.failure = e;
    }

    static <T, E extends Exception> Result<T, E> success(T t) {
        return new Result<>(t, null);
    }

    static <T, E extends Exception> Result<T, E> failure(E e) {
        return new Result<>(null, e);
    }

    public <R> Result<R, E> map(Function<T, R> mapper) {
        return isRight() ? Result.success(mapper.apply(this.succeeded)) : Result.failure(this.failure);
    }

    public <R> Result<R, E> flatmap(Function<T, Result<R, E>> mapper) {
        return isRight() ? mapper.apply(this.succeeded) : Result.failure(this.failure);
    }

    public <R> R holdFailure(Function<E, R> mapper) {
        return mapper.apply(this.failure);
    }

    public void holdFailure(Consumer<E> consumer) {
        consumer.accept(this.failure);
    }

    public Optional<T> unwrapSucceeded() { return Optional.ofNullable(this.succeeded); }

    public Optional<E> unwrapFailure() { return Optional.ofNullable(this.failure); }

    public boolean isRight() { return this.failure == null && this.succeeded != null; }
}