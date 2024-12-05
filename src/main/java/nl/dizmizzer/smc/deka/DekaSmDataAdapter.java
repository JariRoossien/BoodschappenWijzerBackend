package nl.dizmizzer.smc.deka;

import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.core.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.deka.detail.fetch.DekaDetailProduct;
import nl.dizmizzer.smc.deka.detail.fetch.DekaDetailService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DekaSmDataAdapter implements SmProductAdapter<DekaSmProduct> {

    private final DekaDetailService detailService;

    public DekaSmDataAdapter(DekaDetailService detailService) {
        this.detailService = detailService;
    }

    @Override
    public Class<DekaSmProduct> getDomainType() {
        return DekaSmProduct.class;
    }

    @Override
    public SmProductGroup adapt(DekaSmProduct product) {
        DekaDetailProduct productDetail = detailService.findProductDetail(product.getProductID());
        if (productDetail == null) { return null; }
        return SmProductGroup.builder()
                .brand(product.getBrand())
                .name(product.getMainDescription() + " " + product.getSubDescription())
                .products(List.of(new SmProduct("Dekamarkt", product.getProductPrices().get(0).getPrice())))
                .gtins(
                        productDetail
                                .getProductBarcodes()
                                .stream().map(code -> Long.parseLong(code.getBarcode()))
                                .toList()
                )
                .build();
    }
}
