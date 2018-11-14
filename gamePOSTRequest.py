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

url = 'http://api.steampowered.com/ISteamApps/GetAppList/v0002/?format=json'
import urllib2
contents = urllib2.urlopen(url).read()
data = json.loads(contents)
print(data)



#print(creator, date, name, url, desc)
	
#conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

#cur = conn.cursor()
#eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
#sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'anime', '" + url + "', 'TRUE')"
#cur.execute(sql)

#conn.commit()
#cur.close()
#conn.close()
