import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FileTest {

    @DataProvider(name = "contents")
    public static Object[][] contents() {
        return new Object[][]{
            {"odd", 1.5},
            {"even", 2},
            {"", 0}
        };
    }

    @Test(dataProvider = "contents")
    public void testGetSize(String content, double size) {
        File file = new File("name", content);
        assertEquals(file.getSize(), size, "Wrong file size");
    }

    @Test
    public void testGetFilename() {
        String filename = "name";
        String content = "content";
        File file = new File(filename, content);
        assertEquals(file.getFilename(), filename, "Wrong file name");
    }
}