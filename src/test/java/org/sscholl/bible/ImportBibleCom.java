package org.sscholl.bible;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sscholl.bible.common.model.dto.BibleDTO;
import org.sscholl.bible.common.model.dto.BookDTO;
import org.sscholl.bible.common.model.enums.Testament;
import org.sscholl.bible.common.service.BookImportService;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public class ImportBibleCom {

    private static BookImportService bookImportService = new BookImportService();

    public static void main(String[] args) throws Exception {


        BibleDTO bibleDTO = new BibleDTO();
        bibleDTO.setFileName("German__Neue_evangelistische_Übersetzung__neue__LTR.txt");
        bookImportService.loadBookConfig(bibleDTO);


//        FileWriter file = new FileWriter(new ClassPathResource(bibleDTO.getFileName()).getFile());
//        file.write();
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(bibleDTO.getFileName()), StandardCharsets.UTF_8);


        String path = "/de/bible/877/GEN.1.nbh";
//        path = "/de/bible/877/LEV.6.nbh";
//        path = "/de/bible/877/EST.1.nbh";
//        path = "/de/bible/877/PSA.140.nbh";
//        path = "/de/bible/877/REV.1.nbh";

        while (!path.isEmpty()) {

            Document doc = Jsoup.connect("https://www.bible.com" + path)
                    .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11")
                    .timeout(1000000)
                    .get();
//            System.out.println(doc.title());


            AtomicReference<BookDTO> selectedBook = new AtomicReference<>();
            for (Element booksGroup : doc.body().getElementsByClass("book-list")) {
                for (Element bookGroup : booksGroup.getElementsByClass("active")) {
                    String bookName = bookGroup.ownText().toLowerCase();
                    for (BookDTO bookDTO : bibleDTO.getBooks()) {
                        bookDTO.getShortcuts().forEach(s -> {
                            if (s.equals(bookName)) selectedBook.set(bookDTO);
                        });
                    }
                }
            }
            BookDTO myBook = selectedBook.get();
            if (myBook == null) {
                throw new Exception("No book found: " + doc.body().getElementsByClass("book-list"));
            }


            int chapterNumber = -1;
            for (Element chapterGroup : doc.body().getElementsByClass("chapter")) {
                for (Element sub : chapterGroup.children()) {
                    if (sub.hasClass("label")) {
                        chapterNumber = Integer.parseInt(sub.ownText());
                    }
                }
                int verseNumber = -1;
                String verseText = "";
                for (Element verseGroup : doc.body().getElementsByClass("verse")) {

                    int newVerseNumber = -1;
                    String newVerseText = "";
                    for (Element sub : verseGroup.children()) {
                        if (sub.hasClass("label")) {
                            newVerseNumber = Integer.parseInt(sub.ownText());
                        } else if (sub.hasClass("content")) {
                            newVerseText = newVerseText + " " + sub.text();
                        }
                    }

                    //TODO: aktuell wird dieser Vers doppelt abgespeichert.
                    // manche verse sind in 2 span elemente aufgeteilt:
                    //      <div class="p"><span class="verse v2" data-usfm="LEV.6.2"><span class="label">2</span><span class="content">"Gib Aaron und seinen Söhnen folgende Weisungen weiter: </span></span></div>
                    //      <div class="s"><span class="heading">... für das Brandopfer</span></div>
                    //      <div class="p"><span class="verse v2" data-usfm="LEV.6.2"><span class="content"> Für das Brandopfer gilt folgende Anordnung: Es muss die ganze Nacht über auf dem Altarfeuer bleiben und das Feuer muss bis zum Morgen in Brand gehalten werden. </span></span></div>
                    if (newVerseNumber == -1) {
                        // der vers ist eine fortsetzung des vorherigen
                        if (verseNumber == -1 || newVerseText.trim().length() < 1) {
                            throw new Exception("No verse found: " + verseGroup);
                        } else {
                            verseText = verseText + " " + newVerseText;
                        }
                    } else {
                        // neuer vers gefunden
                        verseText = newVerseText.trim();
                        verseNumber = newVerseNumber;
                    }
//                    System.out.println("Kap " + chapterNumber + " Vers " + verseNumber + "   " + verseText);

                    String id = String.format("%02d", myBook.getNumber());
                    String testament;
                    if (Testament.OT.equals(myBook.getTestament())) {
                        testament = "O";
                    } else if (Testament.NT.equals(myBook.getTestament())) {
                        testament = "N";
                    } else {
                        throw new IllegalArgumentException("testament:" + myBook.getTestament() + " is not valid.");
                    }
                    System.out.println(id + testament + "||" + chapterNumber + "||" + verseNumber + "||" + verseText);
                    writer.write(id + testament + "||" + chapterNumber + "||" + verseNumber + "||" + verseText);
                    writer.write("\n");
                }
            }
            Elements nextElements = doc.body().getElementsByClass("next-arrow");
            for (Element next : nextElements) {
                for (Element sub : next.children()) {
                    if (sub.hasAttr("href")) {
                        path = sub.attr("href");
                    } else {
                        path = "";
                    }
                }
            }

            // stop loop on last number
            if (myBook.getNumber() >= 66 && chapterNumber >= 22) break;
        }

        writer.close();
    }

}
