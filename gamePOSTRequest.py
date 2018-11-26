import requests
import json
import psycopg2
import sys
import datetime

query = '''
query {
    applist{
        apps{
            appId
        }
    }
}
'''

url = 'http://store.steampowered.com/api/appdetails?appids=' + sys.argv[1]
import urllib2
contents = urllib2.urlopen(url).read()
data = json.loads(contents)
if data[sys.argv[1]]["success"] == False:
    exit(1)
#print(data)


#879870
start_node = data[sys.argv[1]]["data"]

creator = start_node["developers"][0]
date = str(start_node["release_date"]["date"])
name = start_node["name"]
url = start_node["header_image"]
desc = start_node["short_description"]
desc =desc.replace("'", "")

date_format = datetime.datetime.strptime(date, "%d %b, %Y")
date_format = date_format.strftime("%Y-%m-%d")

print(creator, date_format, name, url, desc)
	
conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

cur = conn.cursor()
eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'game', '" + url + "', 'TRUE')"
cur.execute(sql)

conn.commit()
cur.close()
conn.close()
