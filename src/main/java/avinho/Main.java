package avinho;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls", "XLS");
        chooser.setFileFilter(filter);

        /*
        * MULTI FILES
        chooser.setMultiSelectionEnabled(true);
        int returnVal = chooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            List<File> files = new ArrayList<>(Arrays.asList(chooser.getSelectedFiles()));
            files.forEach((file -> {
                String path = file.getPath();
                ExcelRelatorio.readExcel(path);
            }));
        }*/

        int returnVal = chooser.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String path = file.getAbsolutePath();
            ExcelRelatorio.readExcel(path);
        }
        System.exit(0);
    }
}