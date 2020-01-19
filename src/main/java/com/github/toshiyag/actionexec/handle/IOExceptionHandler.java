package com.github.toshiyag.actionexec.handle;

import java.io.IOException;
import java.util.function.Consumer;

public class IOExceptionHandler extends ExceptionHandler<IOException> {

    public IOExceptionHandler(Consumer<IOException> consumer) {
        super(IOException.class, consumer);
    }
}
