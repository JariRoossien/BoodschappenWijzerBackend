package nl.dizmizzer.smc.scraper.coop;

import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.scraper.base.interfaces.SmProductAdapter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoopToProductGroupConverter implements SmProductAdapter<CoopSmProduct> {

    @Override
    public Class<CoopSmProduct> getDomainType() {
        return CoopSmProduct.class;
    }

    @Override
    public SmProductGroup adapt(CoopSmProduct product) {
        return SmProductGroup.builder()
                .name(product.getName())
                .gtins(List.of(Long.parseLong(product.getSku())))
                .products(List.of(new SmProduct("COOP", product.getSalePrice().getValue())))
                .build();
    }
}
