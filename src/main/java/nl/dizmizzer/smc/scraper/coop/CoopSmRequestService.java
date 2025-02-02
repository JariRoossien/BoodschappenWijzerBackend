package nl.dizmizzer.smc.scraper.coop;

import nl.dizmizzer.smc.scraper.base.SmRequestService;
import nl.dizmizzer.smc.scraper.base.SmProductGroupService;

//@Service
public class CoopSmRequestService extends SmRequestService<CoopSmProduct> {

    public CoopSmRequestService(SmProductGroupService groupService,
                                CoopJsonDataProvider jsonRequest,
                                CoopToProductGroupConverter coopProductAdapter) {
        super(groupService, jsonRequest, coopProductAdapter, CoopSmProduct.class);

//        processRequest();
    }
}
