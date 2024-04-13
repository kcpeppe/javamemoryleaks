package com.kodewerk.db;

import com.kodewerk.stock.ClosingPriceList;
import com.kodewerk.stock.ClosingPrice;

public interface ClosingPriceDataSource {

    public ClosingPriceList load( String ticker) throws ClosingPriceDataSourceException;
    public ClosingPrice getLatestClosingPrice( String ticker) throws ClosingPriceDataSourceException;
    
}
