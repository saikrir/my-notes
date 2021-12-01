package org.saikrishna.apps.mynotes.exceptions;

public class PreExistingEntityException extends RuntimeException{
    public PreExistingEntityException(String name) {
        super("Resource with name " + name  + " exists!");
    }
}
