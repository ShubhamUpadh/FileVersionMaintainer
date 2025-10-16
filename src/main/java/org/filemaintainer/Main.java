package org.filemaintainer;

import org.filemaintainer.v2.FileMaintainerV2;
import org.filemaintainer.v3.FileMaintainerV3;
import org.filemaintainer.v3.FilePersisterReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        FileMaintainerV3 f = new FileMaintainerV3();
        f.initiateFile("First");
        f.writeDataToFile("First","Hello");
        f.writeDataToFile("Second", "Hello2");
        f.initiateFile("Second");
        f.writeDataToFile("Second", "Hello2");
        f.writeDataToFile("Second", "Hello3");
        f.writeDataToFile("Second", "Hello4");
        f.writeDataToFile("Second", "Hello5");
        f.getDataInVersion("Second",3);
        f.rollback("Second", 1);
        f.getListOfDocs();
        FilePersisterReader fpr = new FilePersisterReader();
        fpr.persistFile(f);

//        FileMaintainerV2 f = new FileMaintainerV2();
//        f.create("Hello");
//        f.update("Hello world");
//        f.update("Hello world again");
//        f.update("Hello world thrice");
//        f.update("Hello world fource");
//        f.rollback(10);
//        f.update("Hello world fives");
//        f.rollback(2);
//        f.update("Adding this haha");
//        f.update("Adding this haha2");
//        f.update("Adding this haha3");
//        f.update("Adding this haha4");
//        f.update("Adding this haha5");
//
//        for (int i = 1; i <= 5; i++) {
//            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
//            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
//            System.out.println("i = " + i);
//        }
    }
}