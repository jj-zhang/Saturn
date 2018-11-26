import requests
import json
import psycopg2
import sys
import scraper

query = '''
query ($id: Int){
Media (id: $id, type: ANIME) {
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
#15125
variables = {
    'id': sys.argv[1]
}

url = 'https://graphql.anilist.co'
response = requests.post(url, json={'query': query, 'variables': variables})
data = json.loads(response.text)
if data.has_key("errors"):
    exit(1)
#print(data)

start_node = data["data"]["Media"]

# Get all necessary info from json
creator = start_node["studios"]["nodes"][0]["name"]
date = str(start_node["startDate"]["year"]) + "-" + str(start_node["startDate"]["month"]) +"-"+ str(start_node["startDate"]["day"])
name = start_node["title"]["english"]
url = start_node["coverImage"]["large"]
desc = start_node["description"]

# Change from unicode str
creator = scraper.uni_to_str(creator)
name = scraper.uni_to_str(name)
url= scraper.uni_to_str(url)
desc = scraper.uni_to_str(desc)
desc = scraper.desc.replace("'", "")

print(creator, date, name, url, desc)
	
scraper.scrape(creator, name, desc, date, "anime", url)