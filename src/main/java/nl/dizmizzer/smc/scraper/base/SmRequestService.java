package nl.dizmizzer.smc.scraper.base;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nl.dizmizzer.smc.scraper.base.interfaces.SmJsonDataProvider;
import nl.dizmizzer.smc.scraper.base.interfaces.SmProductAdapter;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SmRequestService<T> {

    private final static Logger logger = Logger.getLogger(SmRequestService.class.getName());

    private final SmProductGroupService groupService;
    private final SmJsonDataProvider jsonRequest;
    private final SmProductAdapter<T> productToGroupAdapter;
    private final SmProductExtractor<T> jsonProductExtractor;
    private final Class<T> typeToken;

    protected SmRequestService(SmProductGroupService groupService, SmJsonDataProvider jsonRequest, SmProductAdapter<T> jsonToSmProductAdapter, Class<T> typeToken) {
        this.groupService = groupService;
        this.jsonRequest = jsonRequest;
        this.productToGroupAdapter = jsonToSmProductAdapter;
        this.typeToken = typeToken;
        this.jsonProductExtractor = new SmProductExtractor<>(new Gson());

//        processRequest();
    }

    /**
     * Executes the request to process and adapt AH supermarket products.
     */
    public void processRequest() {
        logger.log(Level.INFO, typeToken.getName() + " performs new web-scraping task.");
        JsonArray jsonData = jsonRequest.retrieveJsonData();
        parseAndStoreProducts(jsonData);
    }

    private void parseAndStoreProducts(JsonArray jsonData) {
        Optional.ofNullable(jsonData)
                .ifPresent(cards -> {
                    List<T> products = jsonProductExtractor.extractProducts(cards, typeToken);
                    storeProducts(products);
                });
    }


    private void storeProducts(List<T> products) {
        products.forEach(product -> groupService.addOrMerge(productToGroupAdapter.adapt(product)));
    }

}
