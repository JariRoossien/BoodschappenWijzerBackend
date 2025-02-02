package nl.dizmizzer.smc.scraper.vomar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.scraper.base.interfaces.SmJsonDataProvider;
import nl.dizmizzer.smc.scraper.vomar.entity.VomarSmProduct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class VomarJsonDataProvider implements SmJsonDataProvider {

    private static final Logger LOGGER = Logger.getLogger(VomarJsonDataProvider.class.getName());
    private static final String FILE_PATH = "testdata/vomar/vomar_komkommer_data.json";

    @Override
    public Class<VomarSmProduct> getDomainType() {
        return VomarSmProduct.class;
    }

    /**
     * Reads a JSON file and parses it into a JsonObject.
     *
     * @return the parsed JsonObject, or an empty JsonObject if an error occurs.
     */
    public JsonArray retrieveJsonData() {
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
