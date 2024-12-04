package nl.dizmizzer.smc.vomar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
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
public class VomarJsonDataProvider implements SmJsonDataProvider {

    private static final Logger LOGGER = Logger.getLogger(VomarJsonDataProvider.class.getName());
    private static final String FILE_PATH = "testdata/vomar_testdata.json";

    /**
     * Reads a JSON file and parses it into a JsonObject.
     *
     * @return the parsed JsonObject, or an empty JsonObject if an error occurs.
     */
    public JsonElement retrieveJsonData() {
        Gson gson = new Gson();
        try {
            // Read the file content as a single string
            String jsonContent = Files.readString(Path.of(FILE_PATH));
            // Parse and return the JSON object
            return gson.fromJson(jsonContent, JsonArray.class);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading JSON file: " + FILE_PATH, e);
            // Return an empty JsonObject in case of an error
            return new JsonArray();
        }
    }
}
