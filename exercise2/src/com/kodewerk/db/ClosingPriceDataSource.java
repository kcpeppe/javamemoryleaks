package com.kodewerk.db;

import com.kodewerk.stock.ClosingPriceList;
import com.kodewerk.stock.ClosingPrice;
import com.kodewerk.stock.TickerSymbol;

public interface ClosingPriceDataSource {

    public ClosingPriceList load( TickerSymbol ticker) throws ClosingPriceDataSourceException;
    public ClosingPrice getLatestClosingPrice( TickerSymbol ticker) throws ClosingPriceDataSourceException;
    
}
