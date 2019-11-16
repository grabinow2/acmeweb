package statusmgr.decorators;

/**
 * @author
 * @version
 *
 * Decorator for StatusReporterComponents that add the details of the extensions in use by the server
 * to the status report.
 *
 * Currently implemented with concrete strings, but future versions will have appropriate method(s) to handle this
 * correctly.
 */
public class ExtensionsDetailDecorator extends StatusReporterDecorator {

    public ExtensionsDetailDecorator(StatusReporterComponent component){
        super(component);
    }

    @Override
    public String getCurrentServerStatus(){
        return super.getCurrentServerStatus() + ", and is using these extensions - [Hypervisor, Kubernetes, RAID-6]";
    }

}
