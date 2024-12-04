package nl.dizmizzer.smc.ah.adapter;

import nl.dizmizzer.smc.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.core.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AhSmProductAdapter implements SmProductAdapter<AHSmProduct> {

    @Override
    public SmProductGroup adapt(AHSmProduct product) {
        SmProduct smProduct = new SmProduct("AH", product.getPrice().getNow());

        return SmProductGroup
                .builder()
                .name(product.getTitle().replace(product.getBrand(), "").trim())
                .brand(product.getBrand())
                .gtins(new java.util.ArrayList<>(product.getGtins()))
                .unit(product.getPrice().getUnitSize())
//                .unitSize(5) // TODO: Read proper Unit size
                .products(List.of(smProduct))
                .build();
    }
}
