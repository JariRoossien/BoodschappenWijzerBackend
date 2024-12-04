package nl.dizmizzer.smc.ah.provider;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AHJsonDataProvider implements SmJsonDataProvider {

    private static final Logger LOGGER = Logger.getLogger(AHJsonDataProvider.class.getName());
    private static final String FILE_PATH = "testdata/ah_testdata.json";

    private final Gson gson;

    public AHJsonDataProvider(AHGsonprovider gsonprovider) {
        gson = gsonprovider.getGson();
    }

    /**
     * Reads a JSON file and parses it into a JsonObject.
     *
     * @return the parsed JsonObject, or an empty JsonObject if an error occurs.
     */
    public JsonElement retrieveJsonData() {
        try {
            // Read the file content as a single string
            String jsonContent = Files.readString(Path.of(FILE_PATH));
            // Parse and return the JSON object
            return gson.fromJson(jsonContent, JsonObject.class);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading JSON file: " + FILE_PATH, e);
            // Return an empty JsonObject in case of an error
            return new JsonObject();
        }
    }
}
