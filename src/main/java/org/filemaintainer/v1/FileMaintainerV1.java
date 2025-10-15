package org.filemaintainer.v1;

import java.util.ArrayList;
import java.util.List;

public class FileMaintainerV1 {
    private List<ChangeData> versionList;
    private int head;

    public FileMaintainerV1() {
        this.versionList = new ArrayList<>();
        head = -1;
    }

    public void create(String data){
        if (!versionList.isEmpty()){
            throw new RuntimeException("File already exists");
        }
        ChangeData newFile = new ChangeData( "create", 0, data);
        versionList.add(newFile);
        head = 0;
    }

    public void update(String data){
        if (versionList.isEmpty()){
            throw new RuntimeException("Please create the file -> use create method");
        }
        ChangeData updateFile = new ChangeData("update", versionList.size(), data);
        if (head < versionList.size()){
            versionList.set(head + 1, updateFile);
        }
        versionList.add(updateFile);

    }

    public String getVersion(int v){
        if (v >= versionList.size()){
            throw new RuntimeException("File Version doesn't exist");
        }
        return versionList.get(v).getFileData();
    }

    public void rollback(int v){
        if (v >= head){
            System.out.println("Version number not valid.\nValid Versions = : " + listVersions());
            throw new RuntimeException("File Version doesn't exist");
        }
        else if(v < head){
            head = v;
        }
    }

    public List<Integer> listVersions(){
        return versionList.stream().limit(head).map(ChangeData::getVersionNumber).toList();
    }
}

class ChangeData{
    private String type;
    private int versionNumber;
    private String fileData;

    public ChangeData(String type, int versionNumber, String fileData) {
        this.type = type; // should be made an enum later
        this.versionNumber = versionNumber;
        this.fileData = fileData;
    }

    public String getFileData() {
        return fileData;
    }

    public int getVersionNumber() {
        return versionNumber;
    }
}

