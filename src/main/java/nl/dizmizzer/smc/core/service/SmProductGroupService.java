package nl.dizmizzer.smc.core.service;

import nl.dizmizzer.smc.core.entity.SmProduct;
import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.core.repository.SmGroupRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SmProductGroupService {

    private final SmGroupRepository groupRepository;

    public SmProductGroupService(SmGroupRepository groupRepository) {
        this.groupRepository = groupRepository;

    }

    public void addOrMerge(SmProductGroup group) {

        if (group.getProducts().size() != 1) throw new IllegalArgumentException("There should be only one product");

        List<SmProductGroup> otherGroups = groupRepository.findProductsByGtinList(group.getGtins());

        if (otherGroups.isEmpty()) {
            this.groupRepository.save(group);
            return;
        }
        SmProductGroup toMergeWith = otherGroups.get(0);

        // Merge GTins to a big list
        List<Long> gtins = toMergeWith.getGtins();
        for (Long l : group.getGtins()) {
            if (!gtins.contains(l)) gtins.add(l);
        }
        toMergeWith.setGtins(gtins);

        // Merge Product List
        SmProduct existingProduct = null;
        for (SmProduct product : toMergeWith.getProducts()) {
            if (product.getStore().equals(group.getProducts().get(0).getStore())) {
                existingProduct = product;
                break;
            }
        }

        if (existingProduct == null) {
            toMergeWith.getProducts().add(group.getProducts().get(0));
        } else {
            existingProduct.setPrice(group.getProducts().get(0).getPrice());
        }

        this.groupRepository.save(toMergeWith);
    }


    public List<SmProductGroup> getProducts() {
        List<SmProductGroup> result = new ArrayList<>();

        for (SmProductGroup product : this.groupRepository.findAll()) {
            result.add(product);
        }
        return result;
    }
}
