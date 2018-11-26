import requests
import json
import psycopg2
import datetime
import scraper
import sys
    
query = '''
query {
    applist{
        apps{
            appId
        }
    }
}
'''

# example id = 3896198
url = 'http://www.omdbapi.com/?i=tt' + sys.argv[1] + '&apikey=a5fcc20'
import urllib2
contents = urllib2.urlopen(url).read()
data = json.loads(contents)
#print(data)

creator = data["Director"]
date = data["Released"]
name = data["Title"]
url =data["Poster"]
desc = data["Plot"]
desc =desc.replace("'", "")

date_format = datetime.datetime.strptime(date, "%d %b %Y")
date_format = date_format.strftime("%Y-%m-%d")

creator = scraper.uni_to_str(creator)
name = scraper.uni_to_str(name)
url= scraper.uni_to_str(url)
desc = scraper.uni_to_str(desc)

print(creator, date_format, name, url, desc)
	
scraper.scrape(creator, name, desc, date, "movieTV", url)