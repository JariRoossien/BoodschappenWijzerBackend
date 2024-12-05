package nl.dizmizzer.smc.deka;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Data
public class DekaSmProduct {
    private long ProductID;
    private String ProductNumber;
    private String MainDescription;
    private String SubDescription;
    private String Brand;

    private List<DekaPricePoint> ProductPrices;

    @Data
    public static class DekaPricePoint {
        private long PriceID;
        private double Price;
        private double RegularPrice;
        private double PricePerDefaultUnit;
        private String DefaultUnit;
    }
}
