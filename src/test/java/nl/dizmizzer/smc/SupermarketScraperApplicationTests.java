package nl.dizmizzer.smc;

import nl.dizmizzer.smc.scraper.ah.entity.AHSmProduct;
import nl.dizmizzer.smc.scraper.base.SmRequestService;
import nl.dizmizzer.smc.scraper.base.SmRequestServiceFactory;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.query.SmProductIndex;
import nl.dizmizzer.smc.scraper.base.SmProductGroupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SupermarketScraperApplicationTests {

    @Autowired
    SmRequestServiceFactory factory;

    @Autowired
    SmProductGroupService groupService;

    @Test
    void contextLoads() {
        SmProductIndex index = new SmProductIndex();
        SmRequestService<AHSmProduct> productSmRequestService = factory.create(AHSmProduct.class);
        productSmRequestService.processRequest();

        groupService.getProducts().forEach(index::registerNewProduct);

        performQuery(index, "Lay's naturel");
        performQuery(index, "Lay's Max Paprika");
        performQuery(index, "Lay's BBQ");
        performQuery(index, "Lay's Oven");


    }

    public void performQuery(SmProductIndex index, String q) {
        long start = System.nanoTime();
        List<SmProductGroup> x = index.lookupQuery(q).subList(0, 5);
        long finish = System.nanoTime();
        long diff = finish - start;
        double diffInMs = diff / 1000000.0;
        System.out.println();
        System.out.println("Query: " + q + " (" +  diffInMs + "ms)");
        x.forEach(a -> System.out.println("    " + a.getBrand() + " " + a.getName() + " " + a.getRawunitInfo()));

    }
}
