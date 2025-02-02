package nl.dizmizzer.smc.scraper.vomar.entity;

import lombok.Data;

@Data
public class VomarSmProduct {

    String brand;
    String description;
    String detailedDescription;
    double price;
    String eanPrimary;
}
