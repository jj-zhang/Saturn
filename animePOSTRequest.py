import requests
import json
 query = '''
query ($id: Int) {
  Media (id: $id, type: ANIME) {
    id
    startDate{
      year
      month
      day
    }
    title {
      romaji
      english
      native
    }
  }
}
'''
variables = {
    'id': 15125
}
url = 'https://graphql.anilist.co'
response = requests.post(url, json={'query': query, 'variables': variables})
 jData = json.loads(response.content)
 print("The response contains {0} properties".format(len(jData)))
print("\n")
print jData