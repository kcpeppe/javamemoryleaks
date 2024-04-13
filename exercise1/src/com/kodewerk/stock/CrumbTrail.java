package com.kodewerk.stock;

import java.util.Map;
//import java.util.IdentityHashMap;
import java.util.HashMap;

public class CrumbTrail {

    private Map<String,ClosingPriceList> map;

    public CrumbTrail() {
        this.map = new HashMap<>();
    }

    public void addCrumb( ClosingPriceList list) {
        map.put( list.getTicker(), list);
    }

    public ClosingPriceList getCrumb( String ticker) {		
        return map.get( ticker);
    }
}
