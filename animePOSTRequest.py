import requests
import json

query = '''
query {
Media (search: "naruto", type: ANIME) {
    id
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