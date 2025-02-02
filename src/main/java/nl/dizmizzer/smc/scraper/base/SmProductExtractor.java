package nl.dizmizzer.smc.scraper.base;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class SmProductExtractor<T> {

    private final Gson gson;
    public SmProductExtractor(Gson gson) {
        this.gson = gson;
    }

    public List<T> extractProducts(JsonArray cards, Type type) {
        return cards.asList().stream()
                .map(productJson -> (T) gson.fromJson(productJson, type))
                .toList();
    }

}
