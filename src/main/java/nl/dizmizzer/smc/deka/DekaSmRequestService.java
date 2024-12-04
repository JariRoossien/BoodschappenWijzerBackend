package nl.dizmizzer.smc.deka;

import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.smc.core.SmRequestService;
import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import nl.dizmizzer.smc.core.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.service.SmProductGroupService;

public class DekaSmRequestService extends SmRequestService<DekaSmProduct> {

    public DekaSmRequestService(SmProductGroupService groupService,
                                SmJsonDataProvider jsonRequest,
                                SmProductAdapter<DekaSmProduct> jsonToSmProductAdapter,
                                TypeToken<DekaSmProduct> typeToken) {
        super(groupService, jsonRequest, jsonToSmProductAdapter, typeToken);
    }
}
