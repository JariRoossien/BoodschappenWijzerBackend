package nl.dizmizzer.smc.list.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import nl.dizmizzer.smc.core.entity.SmProductGroup;

@Entity
@Data
public class SmUserList {

    @Id @GeneratedValue
    private long id;

    private String name;

    @Data
    public static class SmUserListItem {
        SmProductGroup product;
        int amount;
    }

}
