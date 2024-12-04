package nl.dizmizzer.smc.core.service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public void addOrMerge(SmProductGroup group) {
        if (group.getProducts().size() != 1) {
            throw new IllegalArgumentException("Exactly one product is required for this operation.");
        }

        List<SmProductGroup> matchingGroups = groupRepository.findProductsByGtinList(group.getGtins());
        if (matchingGroups.isEmpty()) {
            groupRepository.save(group);
        } else {
            SmProductGroup toMergeWith = matchingGroups.get(0);
            merge(group, toMergeWith);
            groupRepository.save(toMergeWith);
        }
    }

    public List<SmProductGroup> getProducts() {
        List<SmProductGroup> result = new ArrayList<>();

        for (SmProductGroup product : this.groupRepository.findAll()) {
            result.add(product);
        }
        return result;
    }

    // Improve querying to not just require 100% name, but actual relevance even if name is misspelled, or
    // allow split queries like "Lay's Max 215gr"
    public List<SmProductGroup> getProductsFiltered(String filter) {
        return this.groupRepository.findAllByNameContainingOrBrandContainingIgnoreCase(filter, filter);
    }

    private int getBrandScore(String brand) {
        return switch (brand) {
            case "AH" -> 5;
            case "Vomar" -> 3;
            case "COOP" -> 4;
            default -> -1;
        };
    }

    private int getHighestBrandScore(SmProductGroup group) {
        return group.getProducts().stream().map(product -> getBrandScore(product.getStore())).max(Integer::compareTo).orElse(-1);
    }

    private void merge(SmProductGroup newGroup, SmProductGroup existingGroup) {
        updateExistingProduct(newGroup, existingGroup);
        if (getHighestBrandScore(newGroup) >= getHighestBrandScore(existingGroup)) {
            updateGroupProperties(newGroup, existingGroup);
        }
        addMissingGtins(newGroup, existingGroup);
    }

    private void updateExistingProduct(SmProductGroup newGroup, SmProductGroup existingGroup) {
        for (SmProduct product : existingGroup.getProducts()) {
            if (product.getStore().equals(newGroup.getProducts().get(0).getStore())) {
                product.setPrice(newGroup.getProducts().get(0).getPrice());
                return;
            }
        }
        existingGroup.getProducts().add(newGroup.getProducts().get(0));
    }

    private void updateGroupProperties(SmProductGroup newGroup, SmProductGroup existingGroup) {
        existingGroup.setName(newGroup.getName());
        existingGroup.setBrand(newGroup.getBrand());
        existingGroup.setUnit(newGroup.getUnit());
        existingGroup.setUnitSize(newGroup.getUnitSize());
    }

    private void addMissingGtins(SmProductGroup newGroup, SmProductGroup existingGroup) {
        for (long gtin : newGroup.getGtins()) {
            if (!existingGroup.getGtins().contains(gtin)) {
                existingGroup.getGtins().add(gtin);
            }
        }
    }
}
