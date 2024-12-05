package nl.dizmizzer.smc.core.interfaces;

import nl.dizmizzer.smc.core.entity.SmProductGroup;

public interface SmProductAdapter<T> {

    Class<T> getDomainType();
    SmProductGroup adapt(T product);
}
