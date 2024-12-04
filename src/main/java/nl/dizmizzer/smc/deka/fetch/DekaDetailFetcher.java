package nl.dizmizzer.smc.deka.fetch;

import nl.dizmizzer.smc.deka.DekaDetailProduct;
import org.springframework.stereotype.Service;

@Service
public interface DekaDetailFetcher {

    DekaDetailProduct fetchFromId(long id);
}
