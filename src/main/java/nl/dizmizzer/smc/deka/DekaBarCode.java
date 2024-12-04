package nl.dizmizzer.smc.deka;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class DekaBarCode {
    @Id
    @GeneratedValue
    private long id;
    private String Barcode;
}
