package com.github.toshiyag.actionexec.handleables;

import com.github.toshiyag.actionexec.ExceptionHandleable;

import java.io.IOException;

public interface IOExceptionHandleable extends ExceptionHandleable<IOException> {
    @Override
    void handle(IOException e);

    @Override
    default Class<IOException> getTargetExceptionClass() {
        return IOException.class;
    }
}
