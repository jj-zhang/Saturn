import requests
import json
import psycopg2
def uni_to_str(unicode_input):
    return unicode_input.encode('ascii','ignore')

query = '''
query {
Media (search: "naruto", type: ANIME) {
    id
    description
    title {
        english
        romaji
        native
    }
    startDate{
        year
        month
        day
    }
    endDate{
        year
        month
        day
    }
    coverImage{
        large
        medium
    }
    isAdult
    studios{
        nodes{
            name
        }
    }
    }
}
'''
variables = {
    'id': 15125
}

url = 'https://graphql.anilist.co'
response = requests.post(url, json={'query': query, 'variables': variables})
data = json.loads(response.text)
#print(data["data"])

start_node = data["data"]["Media"]

creator = start_node["studios"]["nodes"][0]["name"]
date = str(start_node["startDate"]["year"]) + "-" + str(start_node["startDate"]["month"]) +"-"+ str(start_node["startDate"]["day"])
name = start_node["title"]["english"]
url = start_node["coverImage"]["large"]
desc = start_node["description"]

creator = uni_to_str(creator)
name = uni_to_str(name)
url= uni_to_str(url)
desc = uni_to_str(desc)
desc =desc.replace("'", "")

print(creator, date, name, url, desc)
	
conn = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")

cur = conn.cursor()
eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date + "', 'anime', '" + url + "', 'TRUE')"
cur.execute(sql)

conn.commit()
cur.close()
conn.close()

