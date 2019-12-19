package com.acme.statusmgr.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author Gedalia Rabinowitz
 * @version
 *
 * A POJO that represents a DiskStatus and can be used to generate JSON for that status
 */
public class DiskStatus implements StatusResponse {

    /**
     * The command to get some response from the disk on a Windows machine.
     * WARNING: This is a very expensive operation
     */
    private static final String[] DISK_COMMAND = {"cmd", "/C", "Dir", "/S", "C:\\*.java"};

    private long id;
    private String contentHeader;
    private String diskCommandOutput;
    private String statusDesc;


    /**
     * Construct a DiskStatus object.
     *
     * @param id                a numeric identifier/counter of which request this
     * @param contentHeader     info about the request
     */
    public DiskStatus(long id, String contentHeader){
        this.id = id;
        this.contentHeader = contentHeader;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getContentHeader() {
        return contentHeader;
    }

    public String obtainStatusDesc(){
        return getDiskCommandOutput();
    }

    /**
     * Runs the DISK_COMMAND on this runtime.
     * @return The String response of the command
     */
    private String getDiskCommandOutput(){

        Runtime rt = Runtime.getRuntime();

        try {
            Process chkProcess = rt.exec(DISK_COMMAND);

            diskCommandOutput = new BufferedReader(new InputStreamReader(chkProcess.getInputStream()))
                    .lines().collect(Collectors.joining("\n"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return diskCommandOutput;
    }


    @Override
    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }
}
