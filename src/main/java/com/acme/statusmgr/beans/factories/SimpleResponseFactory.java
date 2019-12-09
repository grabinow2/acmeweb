package com.acme.statusmgr.beans.factories;

import com.acme.statusmgr.BadRequestException;
import com.acme.statusmgr.beans.ServerStatus;
import com.acme.statusmgr.decorators.simple.*;

import java.util.List;

public class SimpleResponseFactory implements StatusResponseFactory {

    private long id;
    private String header;
    private List<String> details;

    @Override
    public ServerStatus getServerStatus(long id, String header, List<String> details) {

        this.id = id;
        this.header = header;
        this.details = details;

        ServerStatus baseComp = new SimpleBasicStatusReport(id, header);

        ServerStatus decoratedBaseComp = decorateBaseComp(baseComp);

        return decoratedBaseComp;

    }

    private ServerStatus decorateBaseComp(ServerStatus baseComp) {
        for (String s : details) {
            if (s.equalsIgnoreCase("operations"))
                baseComp = new SimpleOperationsDetailDecorator(id, header, baseComp);

            else if (s.equalsIgnoreCase("memory"))
                baseComp = new SimpleMemoryDetailDecorator(id, header, baseComp);

            else if (s.equalsIgnoreCase("extensions"))
                baseComp = new SimpleExtensionsDetailDecorator(id, header, baseComp);

            else {
                try {
                    throw new BadRequestException("Invalid details option: " + s);
                } catch (BadRequestException e) {
                    e.printStackTrace();
                }
            }
        }
        return baseComp;
    }
}
