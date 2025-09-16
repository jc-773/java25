package com.java25.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sandbox {

    /* StableValue example */
    
    /*
     * As we know, in Java, if you want something that is set only once and never changed, you use final
     * 
     * But final means that you must initialize immediately (usually in the constructor)
     * 
     * Sometimes (not often, but sometimes) you don't want to pay that cost at startup - especially if it's a heavy load
     * You'd honestly rather compute the value only when needed (lazy initializaiton)
     * 
    */
    private static final Logger log = LoggerFactory.getLogger(Sandbox.class); 

    /*
     * StableValue is in java.lang so no import needed btw...
     * 
     * StableValue is like final, but later
     * 
     * You can leave it empty at first, then call it with a value, so that it can lock it in forever
     * 
     * After that it behaves exactly like a final and never changes again
     */
    private final StableValue<Logger> logger = StableValue.of();

    Logger log() {
        // Initializes once (thread-safe), then reuses
        return logger.orElseSet(() -> LoggerFactory.getLogger(Sandbox.class));
    }
}
