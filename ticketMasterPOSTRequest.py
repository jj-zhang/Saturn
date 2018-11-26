import requests
import json
import psycopg2

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
key = 'Dv4TzTBMtO5GJ57Dcrf0Jbxst8fEQHLx'
secret = 'lgAN5MVcjmUM30rB'
url = 'https://app.ticketmaster.com/discovery/v2/events.json?size=2&classificationName=concert&apikey=Dv4TzTBMtO5GJ57Dcrf0Jbxst8fEQHLx'
import urllib2
contents = urllib2.urlopen(url).read()
data = json.loads(contents)
#print(data)

event = data["_embedded"]["events"][0]
print(len(event))


#start_node = data[str(879870)]["data"]

creator = event["promoter"]["name"]
date = event["dates"]["start"]["localDate"]
name = event["name"]
url = event["images"][0]["url"]
desc = event["url"]

creator = uni_to_str(creator)
date = uni_to_str(date)
name = uni_to_str(name)
url= uni_to_str(url)
desc = uni_to_str(desc)

print(creator, date, name, url, desc)
	
conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

cur = conn.cursor()
eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'concert', '" + url + "', 'TRUE')"
cur.execute(sql)

conn.commit()
cur.close()
conn.close()
