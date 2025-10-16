package org.filemaintainer.v2;

import java.time.LocalDateTime;

public class ChangeData{
    private final String type;
    private final int versionNumber;
    private final String fileData;
    private final LocalDateTime time;

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

    public String getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
