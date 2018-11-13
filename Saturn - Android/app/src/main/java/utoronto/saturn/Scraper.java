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
    String url = "";
    String desc = "";
    String dir = "";
    String date= "";
    Document doc;
    abstract int scrape(String title) throws IOException, ParseException;
    int requestHTTP(String title) throws IOException{
        String newTitle = title.replaceAll("\\s+", "_");
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + newTitle).get();
        }
        catch(HttpStatusException e){
            System.out.print("failed");
            return 1;
        }
        return 0;
    };
    abstract void parseFeedBack() throws MalformedURLException, ParseException;
    public static void main(String[] args) throws Exception {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/Your_Name").get();
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
            if(! headline.text().equals(""))
                break;
        }
        Element table = doc.select("table.infobox.vevent").get(0); //select the first table.

        Elements rows = table.select("tr");
        System.out.print(rows.size());
        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Element row = rows.get(i);
            Elements cols = row.select("td");

            if(row.text().contains("Directed by")){
                System.out.println(row.text());
                System.out.print(cols.get(0).text());
            }
            if(row.text().contains("Release date")){
                System.out.println(row.text());
                System.out.print(cols.get(0).text());
                SimpleDateFormat parser=new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                Date longDate = parser.parse(cols.get(0).text());
                String date = new SimpleDateFormat("yyyy-MM-dd").format(longDate);
                System.out.print(date);
            }
//            for(int j = 0; j < cols.size(); j++){
//                if(in2.equals("date")) {
//                    System.out.printf("%s \n", cols.get(j).text());
//                    SimpleDateFormat parser=new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
//                    Date longDate = parser.parse(cols.get(j).text());
//                    String date = new SimpleDateFormat("yyyy-MM-dd").format(longDate);
//                    System.out.println(date);
//                }
//                if(in2.equals("dir"))
//                    System.out.printf("%s \n", cols.get(j).text());
//                in2 = "";
//            }
        }
    }
}