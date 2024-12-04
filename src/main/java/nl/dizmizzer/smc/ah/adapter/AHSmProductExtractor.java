package nl.dizmizzer.smc.ah.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.core.interfaces.SmProductExtractor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AHSmProductExtractor implements SmProductExtractor<AHSmProduct> {

    private final Gson gson;

    public AHSmProductExtractor(Gson gson) {

        this.gson = gson;
    }
    public List<AHSmProduct> extractProducts(JsonArray cards) {
        return cards.asList().stream()
                .map(element -> element.getAsJsonObject().getAsJsonArray("products"))
                .flatMap(products -> products.asList().stream())
                .map(productJson -> gson.fromJson(productJson, AHSmProduct.class))
                .toList();
    }

}
