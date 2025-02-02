package nl.dizmizzer.smc.ah;

import nl.dizmizzer.smc.scraper.ah.AHUnitInfoConverter;
import nl.dizmizzer.smc.scraper.ah.entity.AHSmProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AHUnitInfoConverterTest {

    @Test
    void getUnitSizeKilogramsWithUnitInfo() {
        AHSmProduct product = new AHSmProduct();
        product.setPrice(new AHSmProduct.Price());
        product.getPrice().setUnitInfo(new AHSmProduct.Price.UnitInfo());
        product.getPrice().setNow(1); // 1 euro / 100 grams
        product.getPrice().setUnitSize("100 g");
        product.getPrice().getUnitInfo().setPrice(10);
        product.getPrice().getUnitInfo().setDescription("KG");

        assertEquals(0.1, AHUnitInfoConverter.getUnitSize(product));
        assertEquals("KG", AHUnitInfoConverter.getUnitType(product));
    }

    @Test
    void getUnitSizeKilogramsWithoutUnitInfo() {
        AHSmProduct product = new AHSmProduct();
        product.setPrice(new AHSmProduct.Price());
        product.getPrice().setUnitInfo(new AHSmProduct.Price.UnitInfo());
        product.getPrice().setNow(1); // 1 euro / 100 grams
        product.getPrice().setUnitSize("100 g");

        assertEquals(100, AHUnitInfoConverter.getUnitSize(product));
        assertEquals("g", AHUnitInfoConverter.getUnitType(product));
    }

}
