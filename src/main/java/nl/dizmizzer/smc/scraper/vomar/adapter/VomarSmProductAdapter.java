package nl.dizmizzer.smc.scraper.vomar.adapter;

import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.scraper.base.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.scraper.vomar.entity.VomarSmProduct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VomarSmProductAdapter implements SmProductAdapter<VomarSmProduct> {

    @Override
    public Class<VomarSmProduct> getDomainType() {
        return VomarSmProduct.class;
    }

    @Override
    public SmProductGroup adapt(VomarSmProduct product) {
        System.out.println(product.getBrand() + " " + product.getDescription() + " ");
        SmProduct smProduct = new SmProduct("Vomar", product.getPrice());

        return SmProductGroup
                .builder()
                .name(product.getDescription().replace(product.getBrand(), "").trim())
                .brand(product.getBrand())
                .gtins(List.of(Long.parseLong(product.getEanPrimary())))
//                )
//                .unitSize(5) // TODO: Read proper Unit size
                .products(List.of(smProduct))
                .build();
    }
}
