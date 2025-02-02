package nl.dizmizzer.smc.scraper.base.interfaces;

import com.google.gson.JsonArray;

public interface SmJsonDataProvider {
    Class<?> getDomainType();

    JsonArray retrieveJsonData();
}
