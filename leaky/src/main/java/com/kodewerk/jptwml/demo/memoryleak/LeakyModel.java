package com.kodewerk.jptwml.demo.memoryleak;

/********************************************
 * Copyright (c) 2019 Kirk Pepperdine
 * All right reserved
 ********************************************/

import java.util.*;

public class LeakyModel {
    private final List<LeakingClass> leaker = new ArrayList<>();

    public void leak(long range) {
        char[] aString = "some leaking stuff".toCharArray();
        for (long i = 0; i < range; i++) {
            leaker.add(new LeakingClass(new String(aString), new LeakingField()));
        }
    }
}