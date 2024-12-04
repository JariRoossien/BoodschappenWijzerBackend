package nl.dizmizzer.smc.coop;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.dizmizzer.smc.ah.provider.AHGsonprovider;
import nl.dizmizzer.smc.ah.provider.AHJsonDataProvider;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CoopJsonDataProvider implements SmJsonDataProvider {
    private static final Logger LOGGER = Logger.getLogger(CoopJsonDataProvider.class.getName());
    private static final String FILE_PATH = "testdata/coop_testdata.json";

    private final Gson gson;

    public CoopJsonDataProvider(AHGsonprovider gsonprovider) {
        gson = gsonprovider.getGson();
    }

    @Override
    public JsonArray retrieveJsonData() {
        try {
            // Read the file content as a single string
            String jsonContent = Files.readString(Path.of(FILE_PATH));
            // Parse and return the JSON object
            return gson.fromJson(jsonContent, JsonObject.class).getAsJsonArray("elements");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading JSON file: " + FILE_PATH, e);
            // Return an empty JsonObject in case of an error
            return new JsonArray();
        }
    }
}
