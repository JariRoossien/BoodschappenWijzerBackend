package nl.dizmizzer.smc.core;

import nl.dizmizzer.smc.core.entity.SmProductGroup;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SmProductController {

    private final SmProductGroupService groupService;

    public SmProductController(final SmProductGroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/products")
    public List<SmProductGroup> retrieveAllEntries(@RequestParam(name = "query", required = false) String query) {
        if (query == null) {
            return this.groupService.getProducts();
        }

        return new ArrayList<>();
    }
}
