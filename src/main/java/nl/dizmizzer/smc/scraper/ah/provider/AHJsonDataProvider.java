package nl.dizmizzer.smc.scraper.ah.provider;

import nl.dizmizzer.smc.scraper.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.scraper.base.interfaces.SmJsonDataProvider;

public interface AHJsonDataProvider extends SmJsonDataProvider {

    @Override
    default Class<AHSmProduct> getDomainType() {
        return AHSmProduct.class;
    }
}
