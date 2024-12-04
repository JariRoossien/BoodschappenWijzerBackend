package nl.dizmizzer.smc.ah;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nl.dizmizzer.smc.ah.adapter.AHSmProductExtractor;
import nl.dizmizzer.smc.ah.adapter.AhSmProductAdapter;
import nl.dizmizzer.smc.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.ah.provider.AHJsonDataProvider;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to process and manage AH supermarket products.
 */
@Service
public class AHSmRequestService {

    private final SmProductGroupService groupService;
    private final AHJsonDataProvider jsonRequest;
    private final AhSmProductAdapter productAdapter;
    private final AHSmProductExtractor jsonToSmProductAdapter;
    private final Gson gson;

    public AHSmRequestService(SmProductGroupService groupService, AHJsonDataProvider jsonRequest, AhSmProductAdapter productAdapter) {
        this.groupService = groupService;
        this.jsonRequest = jsonRequest;
        this.productAdapter = productAdapter;
        this.gson = new Gson();
        this.jsonToSmProductAdapter = new AHSmProductExtractor(gson);

        processRequest();
    }

    /**
     * Executes the request to process and adapt AH supermarket products.
     */
    public void processRequest() {
        JsonObject jsonData = jsonRequest.retrieveJsonData().getAsJsonObject();
        parseAndStoreProducts(jsonData);
    }

    private void parseAndStoreProducts(JsonObject jsonData) {
        Optional.ofNullable(jsonData.getAsJsonArray("cards"))
                .ifPresent(cards -> {
                    List<AHSmProduct> products = jsonToSmProductAdapter.extractProducts(cards);
                    storeProducts(products);
                });
    }


    private void storeProducts(List<AHSmProduct> products) {
        products.forEach(product -> groupService.addOrMerge(productAdapter.adapt(product)));
    }
}
