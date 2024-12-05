package nl.dizmizzer.smc.core;

import nl.dizmizzer.smc.core.interfaces.SmJsonDataProvider;
import nl.dizmizzer.smc.core.interfaces.SmProductAdapter;
import nl.dizmizzer.smc.core.service.SmProductGroupService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class SmRequestServiceFactory {

    private final Logger logger = Logger.getLogger(SmRequestServiceFactory.class.getName());

    private final SmProductGroupService smProductGroupService;
    private final Map<Class<?>, SmJsonDataProvider> jsonDataProviders;
    private final Map<Class<?>, SmProductAdapter<?>> productAdapters;


    public SmRequestServiceFactory(SmProductGroupService smProductGroupService,
                                   List<SmJsonDataProvider> jsonDataProviders,
                                   List<SmProductAdapter<?>> productAdapters) {
        this.smProductGroupService = smProductGroupService;
        this.jsonDataProviders = jsonDataProviders.stream()
                .collect(Collectors.toMap(SmJsonDataProvider::getDomainType, Function.identity()));
        this.productAdapters = productAdapters.stream()
                .collect(Collectors.toMap(SmProductAdapter::getDomainType, Function.identity()));
    }

    public List<SmRequestService<?>> buildAllRegisteredServices() {
        List<SmRequestService<?>> services = new ArrayList<>();
        for (Class<?> mClass : jsonDataProviders.keySet()) {
            if (!productAdapters.containsKey(mClass)) {
                logger.log(Level.WARNING, mClass.getName()
                        + " does not have a productAdapter implemented yet.");
                continue;
            }
            logger.log(Level.INFO, mClass.getName() + " registered as new Service.");
            services.add(create(mClass));
        }

        return services;
    }

    public <T> SmRequestService<T> create(Class<T> domainType) {
        SmJsonDataProvider jsonDataProvider = jsonDataProviders.get(domainType);

        SmProductAdapter<T> productAdapter = (SmProductAdapter<T>) productAdapters.get(domainType);

        if (jsonDataProvider == null || productAdapter == null) {
            throw new IllegalArgumentException("No configuration found for domain type: " + domainType);
        }

        return new SmRequestService<>(smProductGroupService, jsonDataProvider, productAdapter, domainType);
    }
}
