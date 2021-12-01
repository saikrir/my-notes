package org.saikrishna.apps.mynotes.exceptions;

import java.util.Collection;

public class NonUniqueResultException extends RuntimeException {

    public NonUniqueResultException(Collection<String> searchResults){
        super(String.join(",", searchResults));
    }
}
