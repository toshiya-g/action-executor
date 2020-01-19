package com.github.toshiyag.actionexec.executor;

import com.github.toshiyag.actionexec.Action;
import com.github.toshiyag.actionexec.ExceptionHandleable;
import com.github.toshiyag.actionexec.ExceptionHandlerPool;

public class ActionExecutor<T, E extends Exception> {

    private final Action<T, E> action;
    private final ExceptionHandleable<E> handler;
    private ExceptionHandlerPool<E> pool = null;

    public ActionExecutor(Action<T, E> action, ExceptionHandleable<E> handler) {
        this.action = action;
        this.handler = handler;
    }

    public ActionExecutor(Action<T, E> action, ExceptionHandleable<E> baseHandler, ExceptionHandlerPool<E> handlerPool) {
        this(action, baseHandler);
        this.pool = handlerPool;
    }

    public Result<T, E> action() {
        return actionExecute();
    }

    private Result<T, E> actionExecute() {
        try {
            return ActionExecutor.attempt(this.action);
        } catch (Exception e) {
            return ActionExecutor.handle((E) e, orElseGetHandler(e));
        }
    }

    private ExceptionHandleable<E> orElseGetHandler(Exception e) {
        return this.pool == null
            ? this.handler
            : this.pool.getMatchedHandler(e).orElse(this.handler);
    }

    private static <T, E extends Exception> Result<T, E> attempt(Action<T, E> action) throws E {
        return Result.success(action.action());
    }

    private static <T, E extends Exception> Result<T, E> handle(E e, ExceptionHandleable<E> handler) {
        handler.handle(e);
        return Result.failure(e);
    }
}
