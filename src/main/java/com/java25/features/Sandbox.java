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

    /* PATTERN MATCHING WITH PRIMITIVE TYPES */

    public static void main(String[] args) {
        System.out.println(process(1));
        System.out.println(process(2.1));
        System.out.println(process(3L));
        System.out.println(process("hello"));
    }

    /*
     * No boxing or wrapping primitives in objects
     * 
     * default is not completely necessary. The compiler will actually create one for you if you don't
     * 
     */
    public static String process(Object input) {
        return switch(input) {
            case int i -> "got a number " + i;
            case double d -> "got a double " + d;
            case String s -> "got a string " + s;
            default -> "no clue";
        };
    }

}
