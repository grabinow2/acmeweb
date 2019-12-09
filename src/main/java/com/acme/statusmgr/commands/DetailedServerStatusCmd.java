package com.acme.statusmgr.commands;

import com.acme.statusmgr.BadRequestException;
import com.acme.statusmgr.beans.StatusResponse;
import com.acme.statusmgr.beans.factories.ComplexResponseFactory;
import com.acme.statusmgr.beans.factories.SimpleResponseFactory;
import com.acme.statusmgr.beans.factories.StatusResponseFactory;

import java.util.List;

public class DetailedServerStatusCmd extends StatusCommand {

    private StatusResponseFactory factory;
    private StatusResponse result;

    private List<String> details;

    public DetailedServerStatusCmd(long id, String template, String name, List<String> details, String levelOfDetail){
        super(id, template, name);

        this.details = details;

        assignFactory(levelOfDetail);
    }

    private void assignFactory(String levelOfDetail) {
        if (levelOfDetail.equalsIgnoreCase("complex"))
            factory = new ComplexResponseFactory();
        else if (levelOfDetail.equalsIgnoreCase("simple"))
            factory = new SimpleResponseFactory();
        else {
            throw new BadRequestException("invalid levelofdetail param was " + levelOfDetail);
        }
    }

    @Override
    public void execute() {
        result = factory.getServerStatus(this.id, String.format(template, name), details);
    }

    @Override
    public StatusResponse getResults() {
        return result;
    }

}
