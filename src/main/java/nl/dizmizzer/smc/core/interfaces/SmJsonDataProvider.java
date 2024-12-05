package nl.dizmizzer.smc.core.interfaces;

import com.google.gson.JsonArray;

public interface SmJsonDataProvider {
    Class<?> getDomainType();

    JsonArray retrieveJsonData();
}
