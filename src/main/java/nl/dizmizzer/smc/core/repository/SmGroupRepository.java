package nl.dizmizzer.smc.core.repository;

import nl.dizmizzer.smc.core.entity.SmProductGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmGroupRepository extends CrudRepository<SmProductGroup, Long> {

    @Query("SELECT p FROM SmProductGroup p WHERE EXISTS " +
            "(SELECT gtin FROM p.gtins gtin WHERE gtin IN :gtins)")
    List<SmProductGroup> findProductsByGtinList(@Param("gtins") List<Long> gtins);

    List<SmProductGroup> findAllByNameContainingOrBrandContainingIgnoreCase(String name, String brand);
}
