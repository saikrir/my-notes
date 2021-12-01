package org.saikrishna.apps.mynotes.exceptions;

public class NoResultsFoundException extends RuntimeException {
   public NoResultsFoundException(Types type) {
       super("No SearchResults of Type " + type.toString() + " Found! ");
   }


    public static enum Types {
        Category,
        Notes
    }
}

