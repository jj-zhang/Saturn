package utoronto.saturn;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class AnimeScraper extends Scraper {
    private String url;
    private String desc;
    private String dir;
    private String date;
    @Override
    int scrape(String title) throws IOException, ParseException{
        int ret = requestHTTP(title);
        if(ret == 1)
            return 1;
        parseFeedBack();
        DatabaseUtilities.addRowEvent(dir, title, desc, date, "anime", url, true);
        return 0;
    }
    @Override
    int requestHTTP(String title) throws IOException {
        String newTitle = title.replaceAll("\\s+", "_");
        try {
            super.doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + newTitle).get();
        }
        catch(HttpStatusException e){
            System.out.print("failed");
            return 1;
        }
        return 0;
    }
    @Override
    void parseFeedBack() throws MalformedURLException, ParseException {
        //image url
        Elements element = doc.select("table.infobox img");
        for (Element headline : element) {
            URL u = new URL("https:" + headline.attr("src"));
            url = "https:" + headline.attr("src");
            break;
        }
        element = doc.select("#mw-content-text p");
        for (Element line : element) {
            desc = line.text();
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
                    in2 = "date";
                }
                if(row.text().contains("Directed by")) {
                    in2 = "dir";
                }
            }
            for(int j = 0; j < cols.size(); j++){
                if(in2.equals("date")) {
                    //System.out.printf("%s \n", cols.get(j).text());
                    SimpleDateFormat parser=new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                    Date longDate = parser.parse(cols.get(j).text());
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(longDate);
                    this.date = date;
                    indicator = 0;
                }
                if(in2.equals("dir"))
                    dir = cols.get(j).text();
                in2 = "";
            }
        }
    }
    public static void main(String[] args) throws Exception {
        AnimeScraper anime = new AnimeScraper();
        anime.scrape("One Piece");
    }
}