package business_logic;

import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CrawlerConfigContentTest {

    @Test
    public void crawlByContentNormal() throws IOException, TesseractException {
        String samplePath = "/home/heshan/spsTest/licat/Applications of Linear Alge.pdf";
        String search_content = "linear algebra";
        assertTrue(new CrawlerConfigContent().crawlByContent(false, samplePath, search_content));
    }

    @Test
    public void crawlByContentAdvanced() throws IOException, TesseractException {
        String samplePath = "/home/heshan/spsTest/licat/Applications of Linear Alge.pdf";
        String search_content = "linear algebra";
        assertTrue(new CrawlerConfigContent().crawlByContent(true, samplePath, search_content));
    }
}