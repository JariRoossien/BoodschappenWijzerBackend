package nl.dizmizzer.smc.vomar.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.core.interfaces.SmProductExtractor;
import nl.dizmizzer.smc.vomar.entity.VomarSmProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VomarSmProductExtractor implements SmProductExtractor<VomarSmProduct> {

    private final Gson gson;

    public VomarSmProductExtractor(Gson gson) {

        this.gson = gson;
    }
    public List<VomarSmProduct> extractProducts(JsonArray cards) {
        return cards.asList().stream()
                .map(productJson -> gson.fromJson(productJson, VomarSmProduct.class))
                .toList();
    }

}
