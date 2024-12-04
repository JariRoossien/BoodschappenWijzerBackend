package nl.dizmizzer.smc.core.interfaces;

import com.google.gson.JsonArray;
import nl.dizmizzer.smc.vomar.entity.VomarSmProduct;

import java.util.List;

public interface SmProductExtractor<T> {

    List<T> extractProducts(JsonArray cards);

}
