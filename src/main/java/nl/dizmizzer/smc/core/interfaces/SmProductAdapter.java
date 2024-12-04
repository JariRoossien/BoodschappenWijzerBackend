package nl.dizmizzer.smc.core.interfaces;

import nl.dizmizzer.smc.core.entity.SmProductGroup;

public interface SmProductAdapter<T> {

    SmProductGroup adapt(T product);
}
