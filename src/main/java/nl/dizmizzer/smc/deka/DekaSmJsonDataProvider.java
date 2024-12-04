package nl.dizmizzer.smc.deka;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;

public class DekaSmJsonDataProvider implements SmJsonDataProvider {

    private final Gson gson;

    public DekaSmJsonDataProvider(Gson gson) {
        this.gson = gson;
    }

    @Override
    public JsonArray retrieveJsonData() {
        return null;
    }
}
