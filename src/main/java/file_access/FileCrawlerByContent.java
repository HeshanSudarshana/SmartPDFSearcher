package file_access;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileCrawlerByContent {

    private String filePath;
    private String searchText;

    public FileCrawlerByContent(String filePath, String searchText) {
        this.filePath = filePath;
        this.searchText = searchText;
    }

    public boolean crawlByContentNormal() {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            PDDocument pdfDocument = PDDocument.load(fis);
            int numPages = pdfDocument.getPages().getCount();

            System.out.println("num of pages: " + numPages );

            PDFTextStripper stripper = new PDFTextStripper ();

            // get text of a certain page
            String content;

            // loop through a range of pages
            for (int i = 1 ; i <= numPages ; i++) {
                stripper.setStartPage(i);
                stripper.setEndPage(i);
                content = stripper.getText(pdfDocument);
                if(content.toLowerCase().contains(searchText.toLowerCase())) {
                    return true;
                }
            }

            return false;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public boolean crawlByContentAdvanced() throws IOException, TesseractException {
        File pdfFile = new File(filePath);
        Tesseract instance = new Tesseract();
        File [] files = net.sourceforge.tess4j.util.PdfUtilities.convertPdf2Png(pdfFile);
        for (int i=0; i<files.length; i++) {
            String pageContent = instance.doOCR(files[i]);
            if (pageContent.toLowerCase().contains(searchText.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
