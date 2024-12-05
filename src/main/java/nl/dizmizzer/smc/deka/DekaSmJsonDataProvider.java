package nl.dizmizzer.smc.deka;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DekaSmJsonDataProvider implements SmJsonDataProvider {

    private final Gson gson;
    private final static String FILE_PATH = "testdata/deka/deka_testdata.json";

    public DekaSmJsonDataProvider(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Class<DekaSmProduct> getDomainType() {
        return DekaSmProduct.class;
    }

    @Override
    public JsonArray retrieveJsonData() {
        try {
            String jsonContent = Files.readString(Path.of(FILE_PATH));
            return gson.fromJson(jsonContent, JsonArray.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
