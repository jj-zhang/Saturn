import psycopg2

# function to change unicode to str
def uni_to_str(unicode_input):
    if unicode_input is not None:
        return unicode_input.encode('ascii','ignore')
    
# insert data into database
def scrape(creator, name, desc, date, media_type, url):
    if creator is None or name is None or desc is None or date is None or url is None:
        return 1
    connection = psycopg2.connect(host="tantor.db.elephantsql.com",database="tjlevpcn", user="tjlevpcn", password="SlQEEkbB5hwPHBQxbyrEziDv7w5ozmUu")
    
    cursor = connection.cursor()
    eventsColumn = "(id, creator, name, description, date, type, url, isglobal)";
    sql = "INSERT INTO events " + eventsColumn +" VALUES (NEXTVAL('event_id'), '" + creator + "','" + name + "', '" + desc + "', '" + date +"', '" + media_type + "', '"+ url + "', 'TRUE')"
    
    try:
        cursor.execute(sql)
        connection.commit()
    except:
        connection.rollback()
        return 1
    
    cursor.close()
    connection.close()    
    return 0