package nl.dizmizzer.smc.core;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmRequestScheduler {

    private final List<SmRequestService<?>> requestServices;

    public SmRequestScheduler(SmRequestServiceFactory requestServiceFactory) {
        this.requestServices = requestServiceFactory.buildAllRegisteredServices();

        // TODO: Move this to a daily or weekly scheduled task.
        performRequests();
    }


    public void performRequests() {
        requestServices.forEach(SmRequestService::processRequest);
    }
}
