package io.fourfinanceit.core.commands;

/**
 * Created by Anna on 03.02.2016.
 */
public class VoidResult implements DomainCommandResult {

    public static final VoidResult INSTANCE = new VoidResult();

    private VoidResult() {

    }

}