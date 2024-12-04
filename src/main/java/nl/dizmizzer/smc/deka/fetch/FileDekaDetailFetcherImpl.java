package nl.dizmizzer.smc.deka.fetch;

import com.google.gson.Gson;
import nl.dizmizzer.smc.ah.provider.AHGsonprovider;
import nl.dizmizzer.smc.deka.DekaDetailProduct;
import org.springframework.core.annotation.Order;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Order(value = 0)
public class FileDekaDetailFetcherImpl implements DekaDetailFetcher {

    private final static String BASE_FOLDER = "testdata/deka/detail";

    private final Gson gson;

    public FileDekaDetailFetcherImpl(AHGsonprovider gson) {
        this.gson = gson.getGson();
    }

    @Override
    public DekaDetailProduct fetchFromId(long id) {

        File file = new File(BASE_FOLDER + "/" + id + ".json");
        if (!file.exists()) {
            return null;
        }

        try {
            Reader reader = new FileReader(file);
            return gson.fromJson(reader, DekaDetailProduct.class);
        } catch (FileNotFoundException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Failed Reading Deka Resource for :" + id);
        }

        return null;
    }
}
