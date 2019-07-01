package org.itstep.service;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class FileManagerServiceTest {

    private static String jpgFileFromPath;
    private static String jpgFileToPath;
    private static String txtFileFromPath;
    private static String txtFileToPath;

    @BeforeAll
    static void setPreData(){
        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");

        jpgFileFromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat.jpg";
        jpgFileToPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "cat_test_copy.jpg";

        txtFileFromPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text.txt";
        txtFileToPath = MAIN_DIR + SEPARATOR + "files" + SEPARATOR + "text_test.txt";
    }


    @Test
    void testGetFileAsByteArrayJPG() {
        byte[] bytes = FileManagerService.getFileAsByteArray(jpgFileFromPath);
        assertNotNull(bytes);
        assertTrue(bytes.length > 0);
    }

    @Test
    void testWriteByteArrayToFile() {
        byte[] bytes = FileManagerService.getFileAsByteArray(jpgFileFromPath);

        FileManagerService.writeByteArrayToFile(bytes, jpgFileToPath);
        byte[] testBytes = FileManagerService.getFileAsByteArray(jpgFileToPath);

        assertNotNull(testBytes);
        assertTrue(testBytes.length > 0);
    }

    @Test
    void testWriteTextToFileWithoutAppend() {
        String text = "some test text\n" +
                        "need to be writen to file.";

        FileManagerService.writeTextToFile(txtFileToPath, text, false);

        String testText = FileManagerService.getTextFromFIle(txtFileToPath);

        assertNotNull(testText);
        assertEquals(testText,text+"\n");

        FileManagerService.writeTextToFile(txtFileToPath, text, false);

        testText = FileManagerService.getTextFromFIle(txtFileToPath);

        assertNotNull(testText);
        assertEquals(testText,text+"\n");
    }

    @Test
    void testWriteTextToFileWithAppend() {
        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerService.writeTextToFile(txtFileToPath, text, false);

        String testText = FileManagerService.getTextFromFIle(txtFileToPath);

        assertNotNull(testText);
        assertEquals(testText,text+"\n");

        FileManagerService.writeTextToFile(txtFileToPath, text, true);

        testText = FileManagerService.getTextFromFIle(txtFileToPath);

        assertNotNull(testText);
        assertEquals(testText,text+ "\n" + text + "\n");
    }

    @Test
    void testGetTextFromFIle() {
        String text = FileManagerService.getTextFromFIle(txtFileFromPath);

        assertNotNull(text);
        assertTrue(text.contains("someLine1\n"));
        assertTrue(text.contains("someLine2\n"));
        assertTrue(text.contains("Line3\n"));

    }


    @AfterAll
    static void removeTestFiles(){
        File jpgFile = new File(jpgFileToPath);
        File txtFile = new File(txtFileToPath);
        if (jpgFile.isFile()) {
            try {
                Files.delete(jpgFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (txtFile.isFile()){
            try {
                Files.delete(txtFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}