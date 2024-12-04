package nl.dizmizzer.smc.vomar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.smc.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.core.SmRequestService;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import nl.dizmizzer.smc.vomar.adapter.VomarSmProductAdapter;
import nl.dizmizzer.smc.vomar.entity.VomarSmProduct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service to process and manage AH supermarket products.
 */
@Service
public class VomarSmRequestService extends SmRequestService<VomarSmProduct> {


    public VomarSmRequestService(SmProductGroupService groupService, VomarJsonDataProvider jsonRequest, VomarSmProductAdapter productAdapter) {
        super(groupService, jsonRequest, productAdapter, new TypeToken<>(){});
    }

}
