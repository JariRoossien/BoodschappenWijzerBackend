package nl.dizmizzer.smc.core;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import nl.dizmizzer.smc.core.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.service.SmProductGroupService;

import java.util.List;
import java.util.Optional;


public abstract class SmRequestService<T> {
    private final SmProductGroupService groupService;
    private final SmJsonDataProvider jsonRequest;
    private final SmProductAdapter<T> productToGroupAdapter;
    private final SmProductExtractor<T> jsonProductExtractor;
    private final TypeToken<T> typeToken;

    protected SmRequestService(SmProductGroupService groupService, SmJsonDataProvider jsonRequest, SmProductAdapter<T> jsonToSmProductAdapter, TypeToken<T> typeToken) {
        this.groupService = groupService;
        this.jsonRequest = jsonRequest;
        this.productToGroupAdapter = jsonToSmProductAdapter;
        this.typeToken = typeToken;
        this.jsonProductExtractor = new SmProductExtractor<>(new Gson());

        processRequest();
    }

    /**
     * Executes the request to process and adapt AH supermarket products.
     */
    public void processRequest() {
        JsonArray jsonData = jsonRequest.retrieveJsonData();
        parseAndStoreProducts(jsonData);
    }

    private void parseAndStoreProducts(JsonArray jsonData) {
        Optional.ofNullable(jsonData)
                .ifPresent(cards -> {
                    List<T> products = jsonProductExtractor.extractProducts(cards, typeToken.getType());
                    storeProducts(products);
                });
    }


    private void storeProducts(List<T> products) {
        products.forEach(product -> groupService.addOrMerge(productToGroupAdapter.adapt(product)));
    }

}