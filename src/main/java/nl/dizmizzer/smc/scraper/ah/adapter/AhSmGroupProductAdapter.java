package nl.dizmizzer.smc.scraper.ah.adapter;

import nl.dizmizzer.smc.scraper.ah.AHUnitInfoConverter;
import nl.dizmizzer.smc.scraper.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.scraper.base.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AhSmGroupProductAdapter implements SmProductAdapter<AHSmProduct> {

    @Override
    public Class<AHSmProduct> getDomainType() {
        return AHSmProduct.class;
    }

    @Override
    public SmProductGroup adapt(AHSmProduct product) {
        SmProduct smProduct = new SmProduct("AH", product.getPrice().getNow());
        if (product.getSelectLabel() != null) return null;
        return SmProductGroup
                .builder()
                .name(product.getTitle().replace(product.getBrand(), "").trim())
                .brand(product.getBrand())
                .gtins(new java.util.ArrayList<>(product.getGtins()))
                .unit(AHUnitInfoConverter.getUnitType(product))
                .unitSize(Math.round(AHUnitInfoConverter.getUnitSize(product) * 1000) / 1000.0)
                .rawunitInfo(product.getPrice().getUnitSize())
                .products(List.of(smProduct))

                .build();
    }
}
