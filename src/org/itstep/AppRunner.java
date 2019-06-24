package org.itstep;


import org.itstep.service.FileManagerService;

public class AppRunner {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) {
        String filePath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat.jpg";
        String fileCopyPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat_copy.jpg";

        FileManagerService.copyFile(filePath, fileCopyPath);
    }
}
