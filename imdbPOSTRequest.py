
import requests
import json
import psycopg2

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
print(data)



#start_node = data[str(879870)]["data"]

#creator = data["promoter"]["name"]
#date = data["dates"]["start"]["localDate"]
#name = data["name"]
#url =data["images"][0]["url"]
#desc = data["url"]

#print(creator, date, name, url, desc)
	
#conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

#cur = conn.cursor()
#eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
#sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'anime', '" + url + "', 'TRUE')"
#cur.execute(sql)

#conn.commit()
#cur.close()
#conn.close()
