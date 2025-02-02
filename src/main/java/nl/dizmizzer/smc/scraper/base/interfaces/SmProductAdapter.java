package nl.dizmizzer.smc.scraper.base.interfaces;

import nl.dizmizzer.smc.core.entity.SmProductGroup;

public interface SmProductAdapter<T> {

    Class<T> getDomainType();
    SmProductGroup adapt(T product);
}
