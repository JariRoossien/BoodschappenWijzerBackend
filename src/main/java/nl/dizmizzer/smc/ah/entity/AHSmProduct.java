package nl.dizmizzer.smc.ah.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AHSmProduct {
    private String brand;
    private String title;
    private List<Long> gtins = new ArrayList<>();

    private Price price;

    @Data
    public static class Price {
        private String unitSize;
        private double now;

        private UnitInfo unitInfo;

        @Data
        public static class UnitInfo {
            private double price;
            private String description;
        }
    }
}
