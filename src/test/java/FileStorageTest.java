import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import exception.FileNameAlreadyExistsException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FileStorageTest {

    @DataProvider(name = "fileLists")
    public static Object[] fileLists() {
        return new Object[]{
            new File[]{
                new File("data", ""),
                new File("name", "content")
            },
            new File[]{
                new File("data", "name"),
                new File("file", ""),
                new File("name", "content")
            },
            new File[]{}
        };
    }

    @DataProvider(name = "fileNamesNotExist")
    public static Object[][] fileNamesNotExist() {
        return new Object[][]{
            {"name", "filename"},
            {"filename", "name"},
            {"data", "name"}
        };
    }

    @Test
    public void testWrite() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        String filename = "filename";
        File file = new File(filename, "");
        fileStorage.write(file);
        assertEquals(fileStorage.getFile(filename), file, "The file didn't write");
    }

    @Test(expectedExceptions = FileNameAlreadyExistsException.class)
    public void testWriteException() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        String filename = "filename";
        File file1 = new File(filename, "");
        File file2 = new File(filename, "");
        fileStorage.write(file1);
        fileStorage.write(file2);
    }

    @Test
    public void testWriteBySize() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        String filename = "filename";
        byte[] array = new byte[101];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        File file = new File(filename, generatedString);
        assertFalse(fileStorage.write(file), "The file was written with wrong size");
    }

    @Test
    public void testWriteBySize1() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage(1);
        String filename = "filename";
        File file = new File(filename, "some content");
        assertFalse(fileStorage.write(file), "The file was written with wrong size");
    }

    @Test
    public void testIsExists() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(new File("name", ""));
        assertTrue(fileStorage.isExists("name"), "Not found an existing filename in file list");
    }

    @Test(dataProvider = "fileNamesNotExist")
    public void testIsNotExists(String filename, String newFilename) throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        fileStorage.write(new File(filename, ""));
        assertFalse(fileStorage.isExists(newFilename), "Found an non-existent filename in file list");
    }

    @Test
    public void testDelete() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        String filename = "filename";
        File file = new File(filename, "");
        fileStorage.write(file);
        fileStorage.delete(filename);
        assertFalse(fileStorage.isExists(filename), "The file wasn't delete");
    }

    @Test(dataProvider = "fileLists")
    public void testGetFiles(File[] filesArray) throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        ArrayList<File> files = new ArrayList<>();
        for (File file : filesArray) {
            fileStorage.write(file);
            files.add(file);
        }
        assertEquals(fileStorage.getFiles(), files, "Wrong list of files");
    }

    @Test
    public void testGetFile() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file = new File("name", "");
        fileStorage.write(file);
        assertEquals(fileStorage.getFile("name"), file, "Wrong file");
    }

    @Test
    public void testGetWrongFile() throws FileNameAlreadyExistsException {
        FileStorage fileStorage = new FileStorage();
        File file = new File("filename", "");
        fileStorage.write(file);
        assertNull(fileStorage.getFile("name"), "Found an non-existent file");
    }

    @Test
    public void initFileStorageAvailableSize() {
        FileStorage fileStorage = new FileStorage(1);
        double availableSize = 0;
        try {
            Field field = fileStorage.getClass().getDeclaredField("availableSize");
            field.setAccessible(true);
            availableSize = (Double) field.get(fileStorage);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(availableSize, 1, "Wrong available size");
    }

    @Test
    public void initFileStorageSize() {
        FileStorage fileStorage = new FileStorage(1);
        double maxSize = 0;
        try {
            Field field = fileStorage.getClass().getDeclaredField("maxSize");
            field.setAccessible(true);
            maxSize = (Double) field.get(fileStorage);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(maxSize, 1, "Wrong maxSize");
    }
}