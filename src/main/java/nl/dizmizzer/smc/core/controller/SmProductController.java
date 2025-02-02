package nl.dizmizzer.smc.core.controller;

import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.scraper.base.SmProductGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SmProductController {

    private final SmProductGroupService groupService;

    public SmProductController(final SmProductGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/products")
    public List<SmProductGroup> retrieveAllEntries() {
        return this.groupService.getProducts();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<SmProductGroup> retrieveEntry(@PathVariable(name = "id") final int id) {
        SmProductGroup group = this.groupService.getProductById(id);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(group);
    }

}
