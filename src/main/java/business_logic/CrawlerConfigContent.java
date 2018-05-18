package business_logic;

import file_access.FileCrawlerByContent;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public class CrawlerConfigContent {

    public boolean crawlByContent(boolean advancedSearch, String filePath, String searchText) throws IOException, TesseractException {
        if(advancedSearch) {
            return crawlByContentAdvanced(filePath, searchText);
        } else {
            return crawlByContentNormal(filePath, searchText);
        }
    }

    public boolean crawlByContentNormal(String filePath, String searchText) {
        return (new FileCrawlerByContent(filePath, searchText).crawlByContentNormal());
    }

    public boolean crawlByContentAdvanced(String filePath, String searchText) throws IOException, TesseractException {
        return (new FileCrawlerByContent(filePath, searchText).crawlByContentAdvanced());
    }
}
