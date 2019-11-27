package com.acme.statusmgr.beans.factories;

import com.acme.statusmgr.BadRequestException;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.decorators.complex.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComplexResponseFactory implements StatusResponseFactory {

    private long id;
    private String header;
    private List<String> details;

    @Override
    public ServerStatus getServerStatus(long id, String header, List<String> details) {

        this.id = id;
        this.header = header;
        this.details = details;

        ServerStatus baseComp = new ComplexBasicStatusReport(id, header);

        ServerStatus decoratedBaseComp = decorateBaseComp(baseComp);

        return new ServerStatus(id, header) {
            @Override
            public String getStatusDesc() {
                return decoratedBaseComp.getStatusDesc();
            }
        };
    }

    private ServerStatus decorateBaseComp(ServerStatus baseComp) {
        for (String s : details) {
            if (s.equalsIgnoreCase("operations"))
                baseComp = new ComplexOperationsDetailDecorator(id, header, baseComp);

            else if (s.equalsIgnoreCase("memory"))
                baseComp = new ComplexMemoryDetailDecorator(id, header, baseComp);

            else if (s.equalsIgnoreCase("extensions"))
                baseComp = new ComplexExtensionsDetailDecorator(id, header, baseComp);

            else
                throw new BadRequestException("Invalid details option: " + s);

        }
        return baseComp;
    }
}
