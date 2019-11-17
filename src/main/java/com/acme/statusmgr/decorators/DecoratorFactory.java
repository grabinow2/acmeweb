package com.acme.statusmgr.decorators;

public class DecoratorFactory {

    private StatusReporterDecorator decorator;

    public StatusReporterDecorator getOperationsDecorator(StatusReporterComponent component){
        decorator = new OperationsDetailDecorator(component);
        return decorator;
    }
}
