package nl.dizmizzer.smc.ah;

import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.smc.ah.adapter.AhSmGroupProductAdapter;
import nl.dizmizzer.smc.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.ah.provider.AHJsonDataProvider;
import nl.dizmizzer.smc.core.SmRequestService;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import org.springframework.stereotype.Service;

/**
 * Service to process and manage AH supermarket products.
 */
@Service
public class AHSmRequestService extends SmRequestService<AHSmProduct> {

    public AHSmRequestService(SmProductGroupService groupService, AHJsonDataProvider jsonRequest, AhSmGroupProductAdapter productAdapter) {
        super(groupService, jsonRequest, productAdapter, new TypeToken<AHSmProduct>(){});

        processRequest();
    }

}
