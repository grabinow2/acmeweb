package com.acme.statusmgr.decorators;

/**
 * @author
 * @version
 *
 * The abstract base class for decorators of <code>statusReporterComponent</code>
 *
 * @see StatusReporterComponent
 */
public abstract class StatusReporterDecorator implements StatusReporterComponent {

    private final StatusReporterComponent componentToBeDecorated;

    public StatusReporterDecorator(StatusReporterComponent componentToBeDecorated){
        this.componentToBeDecorated = componentToBeDecorated;
    }

    @Override
    public String getCurrentServerStatus(){
        return componentToBeDecorated.getCurrentServerStatus();
    }

}
