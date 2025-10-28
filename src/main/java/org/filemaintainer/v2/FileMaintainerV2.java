package org.filemaintainer.v2;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import com.github.difflib.UnifiedDiffUtils;
import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;


public class FileMaintainerV2 {
    private List<ChangeData> versionList;

    public List<ChangeData> getVersionList() {
        return versionList;
    }

    private int head;

    public FileMaintainerV2() {
        this.versionList = new ArrayList<>();
        head = -1;
    }

    public void create(String data){
        if (!versionList.isEmpty()){
            throw new RuntimeException("File already exists");
        }
        ChangeData newFile = new ChangeData( "create", 0, "+" + data);
        versionList.add(newFile);
        head = 0;
        System.out.println(getDataWithVersion());
    }

    public void update(String data){
//        diff
        if (versionList.isEmpty()){
            throw new RuntimeException("Please create the file -> use create method");
        }
        String prevVersionData = getVersion(head);
        String diffString = getDiffString(prevVersionData, data); //use diff-match-patch here
        String encodedString = Base64.getEncoder().encodeToString(diffString.getBytes(StandardCharsets.UTF_8));
        ChangeData updateFile = new ChangeData("update", head + 1, encodedString);
//        System.out.println((head < versionList.size()-1));
        if (head < versionList.size()-1){
            // This will be the case when the file has been rolled back
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

    private String getDiffString(String a, String b){
        List<String> original = List.of(a);
        List<String> revised = List.of(b);
        Patch<String> patch = DiffUtils.diff(original, revised);
        List<String> unifiedDiff = UnifiedDiffUtils.generateUnifiedDiff("original", "revised", original, patch, 0);
        return String.join("\n", unifiedDiff);

    }
}

