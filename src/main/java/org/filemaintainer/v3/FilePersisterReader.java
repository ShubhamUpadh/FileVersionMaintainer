package org.filemaintainer.v3;

import com.opencsv.CSVWriter;
import org.filemaintainer.v2.ChangeData;
import org.filemaintainer.v2.FileMaintainerV2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FilePersisterReader {
    public void persistFile(FileMaintainerV3 f){
        try {
            File fileNamesVersionTable = new File("filenames_version_table.csv");
            File fileVersionHistory = new File("file_version_history.csv");

            FileWriter fileNamesVersionTb = new FileWriter(fileNamesVersionTable);
            CSVWriter fileNamesVersionTbWriter = new CSVWriter(fileNamesVersionTb);

            FileWriter fileVersionHistoryTb = new FileWriter(fileVersionHistory);
            CSVWriter fileVersionHistoryTableWriter = new CSVWriter(fileVersionHistoryTb);

            String[] header = {"filename", "versionId"};
            fileNamesVersionTbWriter.writeNext(header);

            header = new String[]{"versionId", "type", "versionNumber", "fileData", "time"};
            fileVersionHistoryTableWriter.writeNext(header);


            int i = 0;
            for (String fileName : f.getFileKeySet()) {
                String[] row = {fileName, Integer.toString(i)};
                fileNamesVersionTbWriter.writeNext(row);
                FileMaintainerV2 fmv2 = f.getFileVersionForFileName(fileName);

                for (ChangeData changeData : fmv2.getVersionList()){
                    row = new String[]{
                            Integer.toString(i),
                            changeData.getType(),
                            Integer.toString(changeData.getVersionNumber()),
                            changeData.getFileData(),
                            String.valueOf(changeData.getTime())
                    };
                    fileVersionHistoryTableWriter.writeNext(row);
                }
                i++;
            }
            fileNamesVersionTbWriter.close();
            fileVersionHistoryTableWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//            private String type;
//            private int versionNumber;
//            private String fileData;
//            private LocalDateTime time;
}
