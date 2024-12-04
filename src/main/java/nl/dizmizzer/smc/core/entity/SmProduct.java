package nl.dizmizzer.smc.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class SmProduct {

    @Id @GeneratedValue private long id;
    String store;
    private double price;

    public SmProduct() {
        this.store = "";
        this.price = 0.0;
    }

    public SmProduct(String store, double price) {
        this.store = store;
        this.price = price;
    }
}
