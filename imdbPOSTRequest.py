
import requests
import json
import psycopg2
import datetime

def uni_to_str(unicode_input):
    if unicode_input is not None:
        return unicode_input.encode('ascii','ignore')
    
query = '''
query {
    applist{
        apps{
            appId
        }
    }
}
'''

url = 'http://www.omdbapi.com/?i=tt3896198&apikey=a5fcc20 '
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

creator = uni_to_str(creator)
name = uni_to_str(name)
url= uni_to_str(url)
desc = uni_to_str(desc)

print(creator, date_format, name, url, desc)
	
conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

cur = conn.cursor()
eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'movie/TV', '" + url + "', 'TRUE')"
cur.execute(sql)

conn.commit()
cur.close()
conn.close()
