package nl.dizmizzer.smc.vomar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import nl.dizmizzer.smc.vomar.adapter.VomarSmProductExtractor;
import nl.dizmizzer.smc.vomar.adapter.VomarSmProductAdapter;
import nl.dizmizzer.smc.vomar.entity.VomarSmProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to process and manage AH supermarket products.
 */
@Service
public class VomarSmRequestService {

    private final SmProductGroupService groupService;
    private final VomarJsonDataProvider jsonRequest;
    private final VomarSmProductAdapter productAdapter;
    private final VomarSmProductExtractor jsonToSmProductAdapter;
    private final Gson gson;

    public VomarSmRequestService(SmProductGroupService groupService, VomarJsonDataProvider jsonRequest, VomarSmProductAdapter productAdapter) {
        this.groupService = groupService;
        this.jsonRequest = jsonRequest;
        this.productAdapter = productAdapter;
        this.gson = new Gson();
        this.jsonToSmProductAdapter = new VomarSmProductExtractor(gson);

        processRequest();
    }

    /**
     * Executes the request to process and adapt AH supermarket products.
     */
    public void processRequest() {
        JsonElement jsonData = jsonRequest.retrieveJsonData();
        parseAndStoreProducts(jsonData);
    }

    private void parseAndStoreProducts(JsonElement jsonData) {
        Optional.ofNullable(jsonData.getAsJsonArray())
                .ifPresent(cards -> {
                    List<VomarSmProduct> products = jsonToSmProductAdapter.extractProducts(cards);
                    storeProducts(products);
                });
    }


    private void storeProducts(List<VomarSmProduct> products) {
        products.forEach(product -> groupService.addOrMerge(productAdapter.adapt(product)));
    }
}
