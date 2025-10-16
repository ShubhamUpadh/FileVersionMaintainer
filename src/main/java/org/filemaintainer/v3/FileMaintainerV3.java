package org.filemaintainer.v3;

import org.filemaintainer.v2.FileMaintainerV2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FileMaintainerV3 {
    private Map<String, FileMaintainerV2> docMaintainerMap = new HashMap<>();

    public void initiateFile(String fileName){
        docMaintainerMap.put(fileName, new FileMaintainerV2());
    }

    public void writeDataToFile(String fileName, String data){
        if (!docMaintainerMap.containsKey(fileName)){
            System.out.println("FileName " + fileName +" Not Found");
            return;
        }
        if (docMaintainerMap.get(fileName).getHead() == -1){
            docMaintainerMap.get(fileName).create(data);
            return;
        }
//        System.out.println(docMaintainerMap.containsKey(fileName));
        docMaintainerMap.get(fileName).update(data);
    }

    public String getDataInVersion(String fileName, int v){
        if (!docMaintainerMap.containsKey(fileName)){
            System.out.println("FileName " + fileName +" Not Found");
        }
        return docMaintainerMap.get(fileName).getVersion(v);
    }

    public void rollback(String fileName, int v){
        if (!docMaintainerMap.containsKey(fileName)){
            System.out.println("FileName " + fileName +" Not Found");
        }

        FileMaintainerV2 f = docMaintainerMap.get(fileName);

        if (v < 0 || v >= f.getHead()){
            System.out.println("Version number not valid.\nValid Versions = : " + f.listVersions());
            System.out.println("File Version doesn't exist");
        }
        else {
            System.out.println("Rolling back to version :" + v);
            f.setHead(v);
            System.out.println(f.getDataWithVersion());
            System.out.println(f.listVersions());
        }
    }

    public void getListOfDocs(){
        System.out.println(String.join("," ,docMaintainerMap.keySet()));
    }

    public Set<String> getFileKeySet(){
        return docMaintainerMap.keySet();
    }

    public FileMaintainerV2 getFileVersionForFileName(String fileName){
        return docMaintainerMap.get(fileName);
    }
}
