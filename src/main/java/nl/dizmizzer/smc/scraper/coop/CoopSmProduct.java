package nl.dizmizzer.smc.scraper.coop;

import lombok.Data;

@Data
public class CoopSmProduct {
    private String name;
    private String productName;
    private String shortDescription;
    private String longDescription;

    private String sku;
    private ListPrice listPrice;

    @Data
    public static class ListPrice {
        private double value;
    }

    private ListPrice salePrice;

    @Data
    public static class CoopAttribute {
        private String name;
        private String value;
    }
}
