package file_access;

import business_logic.CrawlerConfigContent;
import business_logic.CrawlerConfigName;
import business_logic.PDFFile;
import javafx.collections.ObservableList;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class RecursiveWalk extends RecursiveAction {

    private Path dir;
    private ArrayList<File> fileList;
    private String searchText;
    private boolean advancedSearch;
    private ObservableList<PDFFile> pdfFileObservableList;

    public RecursiveWalk(Path dir, ArrayList<File> fileList, String searchText, boolean advancedSearch, ObservableList<PDFFile> pdfFiles) {
        this.dir = dir;
        this.fileList = fileList;
        this.searchText = searchText;
        this.advancedSearch = advancedSearch;
        this.pdfFileObservableList = pdfFiles;
    }

    @Override
    protected void compute() {
        final List<RecursiveWalk> walks = new ArrayList<>();
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    if (!dir.equals(RecursiveWalk.this.dir)) {
                        RecursiveWalk w = new RecursiveWalk(dir, fileList, searchText, advancedSearch, pdfFileObservableList);
                        w.fork();
                        walks.add(w);

                        return FileVisitResult.SKIP_SUBTREE;
                    } else {
                        return FileVisitResult.CONTINUE;
                    }
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String[] type = file.toFile().getName().toString().split("\\.(?=[^\\.]+$)");
                    if (type.length > 1) {
                        if (type[1].equals("pdf")) {
                            try {
                                if(new CrawlerConfigContent().crawlByContent(advancedSearch, file.toFile().getAbsolutePath().toString(), searchText)) {
                                    fileList.add(file.toFile());
                                    pdfFileObservableList.add(new PDFFile(file.toFile()));
                                }
                            } catch (TesseractException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (RecursiveWalk w : walks) {
            w.join();
        }
    }
}