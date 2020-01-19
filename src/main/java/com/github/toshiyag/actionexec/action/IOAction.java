package com.github.toshiyag.actionexec.action;

import com.github.toshiyag.actionexec.Action;

import java.io.IOException;

public interface IOAction<T> extends Action<T, IOException> {
    @Override
    T action() throws IOException;
}
