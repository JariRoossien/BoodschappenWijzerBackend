package nl.dizmizzer.smc.deka.detail.fetch;

import jakarta.persistence.*;
import lombok.Data;
import nl.dizmizzer.smc.deka.DekaBarCode;

import java.util.List;

/**
 * Due to Dekamarkt API being split into query and Detailed response, use the detailed response
 * for general information retrieval.
 * <p>
 * Detailed response requested through https://api.dekamarkt.nl/v1/assortmentcache/283/:product_id
 * <p>
 * For more info, see API.md
 */
@Data
@Entity
public class DekaDetailProduct {
    @Id
    private long ProductID;

    private String MainDescription;
    private String SubDescription;

    private String CommercialContent;
    private String Brand;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<DekaBarCode> ProductBarcodes;


}
