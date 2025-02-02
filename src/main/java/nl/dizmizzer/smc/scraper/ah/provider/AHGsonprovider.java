package nl.dizmizzer.smc.scraper.ah.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nl.dizmizzer.smc.scraper.base.interfaces.Gsonprovider;
import org.springframework.stereotype.Component;

@Component
public class AHGsonprovider implements Gsonprovider {

    private final Gson gson;

    public AHGsonprovider() {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public Gson getGson() {
        return this.gson;
    }
}
