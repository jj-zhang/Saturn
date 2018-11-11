package utoronto.saturn;


import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.net.URL;
import static java.lang.System.exit;


abstract class Scraper {
    Document doc;

    abstract int scrape(String title) throws IOException, ParseException;

    abstract int requestHTTP(String title) throws IOException;

    abstract void parseFeedBack() throws MalformedURLException, ParseException;

    public static void main(String[] args) throws Exception {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/Laid-Back_Camp").get();
        }
        catch(HttpStatusException e){
            System.out.print("failed");
            exit(1);
        }
        System.out.println(doc.title());

        //image url
        Elements element = doc.select("table.infobox img");
        for (Element headline : element) {
            System.out.printf("%s\n\t", headline.attr("src"));
            URL u = new URL("https:" + headline.attr("src"));
            break;
        }

        element = doc.select("#mw-content-text p");
        for (Element headline : element) {
            System.out.printf("%s\n\t", headline.text());
            break;
        }

        Element table = doc.select("table.infobox").get(0); //select the first table.
        Elements rows = table.select("tr");

        int indicator = 0;
        String in2 = "";
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");
            if(row.text().contains("Anime television series") || indicator == 1){
                indicator = 1;
                if(row.text().contains("Original run")) {
                    System.out.printf("%s \n", row.text());

                    in2 = "date";
                }
                if(row.text().contains("Directed by")) {
                    System.out.printf("%s \n", row.text());

                    in2 = "dir";
                }
            }
            for(int j = 0; j < cols.size(); j++){
                if(in2.equals("date")) {
                    System.out.printf("%s \n", cols.get(j).text());
                    SimpleDateFormat parser=new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                    Date longDate = parser.parse(cols.get(j).text());
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(longDate);

                    System.out.println(date);

                    indicator = 0;
                }
                if(in2.equals("dir"))
                    System.out.printf("%s \n", cols.get(j).text());

                in2 = "";
            }
        }
    }
}


