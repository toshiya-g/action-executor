package com.github.toshiyag.actionexec.executors;

import com.github.toshiyag.actionexec.ExceptionHandlerPool;
import com.github.toshiyag.actionexec.action.IOAction;
import com.github.toshiyag.actionexec.executor.ActionExecutor;
import com.github.toshiyag.actionexec.handleables.IOExceptionHandleable;

import java.io.IOException;

public class IOActionExecutor<T> extends ActionExecutor<T, IOException> {

    public IOActionExecutor(IOAction action, IOExceptionHandleable handler) {
        super(action, handler);
    }

    public IOActionExecutor(IOAction action, IOExceptionHandleable baseHandler, ExceptionHandlerPool handlers) {
        super(action, baseHandler, handlers);
    }
}
