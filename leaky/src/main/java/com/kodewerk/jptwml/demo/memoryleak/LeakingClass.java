package com.kodewerk.jptwml.demo.memoryleak;

/********************************************
 * Copyright (c) 2019 Kirk Pepperdine
 * All right reserved
 ********************************************/

import java.util.*;

public class LeakingClass {
    private final Map<String, String> alongForTheRide = new HashMap<>();
    private final String alsoAlongForTheRide;
    private final LeakingField leakingField;

    public LeakingClass(String parameter, LeakingField field) {
        alsoAlongForTheRide = parameter;
        leakingField = field;
    }

    public String toString() {
        var buffer = new StringBuffer(alongForTheRide.toString());
        buffer.append(alsoAlongForTheRide);
        buffer.append(leakingField);
        return buffer.toString();
    }
}