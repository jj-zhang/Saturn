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
public class MovieScraper extends Scraper {

    @Override
    int scrape(String title) throws IOException, ParseException{
        url = "";
        desc = "";
        dir = "";
        date= "";
        int ret = requestHTTP(title);
        if(ret == 1) {
            ret = requestHTTP(title + "_(film)");
            if(ret != 0)
                return 1;
        }
        parseFeedBack();
        if(date.equals("")) {
            ret = requestHTTP(title + "_(film)");
            if(ret != 0)
                return 1;
            parseFeedBack();
            if(date.equals(""))
                return 1;
        }
        DatabaseUtilities.addRowEvent(dir, title, desc, date, "movie", url, true);
        return 0;
    }
    @Override
    int requestHTTP(String title) throws IOException {
        return super.requestHTTP(title);
    }
    @Override
    void parseFeedBack() throws MalformedURLException, ParseException {
        //image url
        Elements element = doc.select("table.infobox img");
        for (Element headline : element) {
            //System.out.printf("%s\n\t", headline.attr("src"));
            URL u = new URL("https:" + headline.attr("src"));
            url = "https:" + headline.attr("src");
            break;
        }
        element = doc.select("#mw-content-text p");
        for (Element headline : element) {
            //System.out.printf("%s\n\t", headline.text());
            desc = headline.text();
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
                dir = cols.get(0).text();
                //System.out.println(row.text());
                //System.out.print(cols.get(0).text());
            }
            if(row.text().contains("Release date")){
                SimpleDateFormat parser=new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                Date longDate = parser.parse(cols.get(0).text());
                String date = new SimpleDateFormat("yyyy-MM-dd").format(longDate);
                this.date = date;
                //System.out.println(row.text());
                //System.out.print(cols.get(0).text());
            }

        }
    }
}