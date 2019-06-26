package org.itstep;


import org.itstep.service.FileManagerService;

public class AppRunner {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) {
        String filePath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text.txt";
        String fileCopyPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text_copy.txt";

        String text = FileManagerService.getTextFromFIle(filePath);
        FileManagerService.writeTextToFile(fileCopyPath, text, true);
    }
}
