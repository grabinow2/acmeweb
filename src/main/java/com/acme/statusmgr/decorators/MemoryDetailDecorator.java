package com.acme.statusmgr.decorators;

/**
 * @author
 * @version
 *
 * Decorator for StatusReporterComponents that add the details of the server's memory to the status report.
 *
 * Currently implemented with concrete strings, but future versions will have appropriate method(s) to handle this
 * correctly.
 */
public class MemoryDetailDecorator extends StatusReporterDecorator {

    public MemoryDetailDecorator(StatusReporterComponent component){
        super(component);
    }

    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and its memory is Running low";
    }
}
