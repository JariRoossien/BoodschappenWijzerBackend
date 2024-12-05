package nl.dizmizzer.smc.deka.fetch;

import nl.dizmizzer.smc.ah.provider.AHGsonprovider;
import nl.dizmizzer.smc.deka.detail.fetch.DekaDetailProduct;
import nl.dizmizzer.smc.deka.detail.fetch.FileDekaDetailFetcherImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileDekaDetailFetcherImplTest {

    FileDekaDetailFetcherImpl fetcher = new FileDekaDetailFetcherImpl(new AHGsonprovider());



    @Test
    public void canFindSuccesfulResourceFromFile() {
        DekaDetailProduct product = fetcher.fetchFromId(14535L);
        assertEquals(14535, product.getProductID());
        assertEquals(1, product.getProductBarcodes().size());
        assertEquals("8710398502322", product.getProductBarcodes().get(0).getBarcode());
    }

    @Test
    public void canNotFindNonExistingResourceFromFile() {
        DekaDetailProduct product = fetcher.fetchFromId(123456789L);
        assertNull(product);
    }

}
