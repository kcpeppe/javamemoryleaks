package com.kodewerk.jptwml.demo.memoryleak;

/********************************************
 * Copyright (c) 2019 Kirk Pepperdine
 * All right reserved
 ********************************************/

public class LeakingField {
    private final String string = "more string stuff to leak";

    public String toString() {
        return string;
    }
}