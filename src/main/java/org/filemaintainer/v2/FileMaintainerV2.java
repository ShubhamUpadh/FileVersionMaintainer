package org.filemaintainer.v2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileMaintainerV2 {
    private List<ChangeData> versionList;
    private int head;

    public FileMaintainerV2() {
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
        System.out.println(getDataWithVersion());
    }

    public void update(String data){
        if (versionList.isEmpty()){
            throw new RuntimeException("Please create the file -> use create method");
        }
        ChangeData updateFile = new ChangeData("update", head + 1, data);
//        System.out.println((head < versionList.size()-1));
        if (head < versionList.size()-1){
            versionList.set(head + 1, updateFile);
        }
        else {
            versionList.add(updateFile);
        }
        head++;
        System.out.println(getDataWithVersion() + " " + (head < versionList.size()-1));
    }

    public String getVersion(int v){
        if (v >= versionList.size() || v < 0){
            return "File Version doesn't exist";
        }
        return versionList.get(v).getFileData();
    }

    public void rollback(int v){

        if (v < 0 || v >= head){
            System.out.println("Version number not valid.\nValid Versions = : " + listVersions());
            System.out.println("File Version doesn't exist");
        }
        else {
            System.out.println("Rolling back to version :" + v);
            head = v;
            System.out.println(getDataWithVersion());
            System.out.println(listVersions());
        }
    }

    public List<Integer> listVersions(){
        return versionList.stream().limit(head+1).map(ChangeData::getVersionNumber).toList();
    }

    public Map<Integer, String> getDataWithVersion(){
        return versionList.stream().limit(head+1).collect(Collectors.toMap(ChangeData::getVersionNumber, ChangeData::getFileData));
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getHead() {
        return head;
    }
}

class ChangeData{
    private String type;
    private int versionNumber;
    private String fileData;
    private LocalDateTime time;

    public ChangeData(String type, int versionNumber, String fileData) {
        this.type = type; // should be made an enum later
        this.versionNumber = versionNumber;
        this.fileData = fileData;
        this.time = LocalDateTime.now();
    }

    public String getFileData() {
        return fileData;
    }

    public int getVersionNumber() {
        return versionNumber;
    }
}

