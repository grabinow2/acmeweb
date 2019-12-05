package com.acme.statusmgr.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author
 * @version
 *
 * A POJO that represents a DiskStatus and can be used to generate JSON for that status
 */
public class DiskStatus implements StatusResponse {

    /**
     * The command to get some response from the disk on a Windows machine
     */
    private static final String[] DISK_COMMAND = {"cmd", "/C", "Dir", "/S", "C:\\*.java"};

    /**
     * The Id of this request
     */
    private long id;
    /**
     * Details about the request (example: name of requester)
     */
    private String contentHeader;
    /**
     * Output issued by OS regarding the command to get some status of the disk
     */
    private String diskCommandOutput;


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

    @Override
    public String getStatusDesc(){
        return getDiskCommandOutput();
    }

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



}
