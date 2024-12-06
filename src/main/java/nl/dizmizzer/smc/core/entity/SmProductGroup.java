package nl.dizmizzer.smc.core.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Entity

@AllArgsConstructor
public class SmProductGroup {

    @GeneratedValue
    @Id
    private long id;

    private String brand;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> gtins;

    /**
     *
     */
    private String unit;

    /**
     * Due to different types of products, we define a unit size by either:
     * - 1 liter (e.g. 500ml bottle -> 0.5 unit)
     * - 1 kg (e.g. 200gr bag -> 0.2 unit)
     * - 1 set (e.g. 1 set of bananas -> 1 unit)
     * - 1 item (e.g. 3 box of detergent -> 3 units)
     */
    private double unitSize;

    /**
     * To implement with Elasticsearch query, we implement the raw info to improve searching for 215gr for example.
     */
    private String rawunitInfo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SmProduct> products;

    public SmProductGroup() {

    }


    public SmProductGroup(String brand, String name, List<Long> gtin, String kg, double unitSize, List<SmProduct> products) {
        this.brand = brand;
        this.name = name;
        this.gtins = gtin;
        this.unit = kg;
        this.unitSize = unitSize;
        this.products = products;
    }
}
