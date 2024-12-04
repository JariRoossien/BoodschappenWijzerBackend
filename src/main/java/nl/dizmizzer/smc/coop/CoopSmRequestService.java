package nl.dizmizzer.smc.coop;

import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.smc.core.SmRequestService;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import org.springframework.stereotype.Service;

@Service
public class CoopSmRequestService extends SmRequestService<CoopSmProduct> {

    public CoopSmRequestService(SmProductGroupService groupService,
                                CoopJsonDataProvider jsonRequest,
                                CoopToProductGroupConverter coopProductAdapter) {
        super(groupService, jsonRequest, coopProductAdapter, new TypeToken<CoopSmProduct>(){});

        processRequest();
    }
}
