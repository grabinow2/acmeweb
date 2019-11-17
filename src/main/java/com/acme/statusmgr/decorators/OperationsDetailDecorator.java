package com.acme.statusmgr.decorators;

/**
 * @author
 * @version
 *
 * Decorator for StatusReporterComponents that add the details of the server's operations to the status report.
 *
 * Currently implemented with concrete strings, but future versions will have appropriate method(s) to handle this
 * correctly.
 */
public class OperationsDetailDecorator extends StatusReporterDecorator {

    public OperationsDetailDecorator(StatusReporterComponent componentToBeDecorated) {
        super(componentToBeDecorated);
    }

    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and is operating normally";
    }

}
