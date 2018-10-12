S@urn/Team 


#### Overview


S@urn is an app/web extension that allows users to browse, search, and be reminded of upcoming media/product releases and events they are excited for. 

For users who constantly forget the release dates or are awaiting the release dates of various forms of media. S@urn allows users to pin media to remind and update their anticipated releases rather than visiting multiple websites to get their fill of whether or not it has been released yet.

We are planning to build an ANDROID app and possibly a browser extension.

Some media that will be featured are movies, TV shows, concerts, anime, and games.
	The app will allow the users to search specific media or categories in which the app will provide the release date of the media
If the media has been released, notify the user
Records a list of media that the user may add to
Our app automatically updates each item’s release date online into a database via scraping
If no search result comes up, keep searching periodically in the background
When a release date is known, start a countdown
Notify user when countdown is done
Extra features:
Notify the user a certain amount of time before actual release date
Recommend trending media to users
Recommend media based on past searches
Categories
Recommend online shops once product is released
Recommend stores based on location of user once product is released
Estimated price also be included along with release date 
Links to popular web stores to buy the media
News/Updates area
Trailers
Possible comments/discussion board
Search/recommend multiple creators

Common use cases:
Users who are anticipating a upcoming release of a media would like to be notified as soon as the product is released.
From our app, a notification is automatically produced upon release date
Users who would like to know the (not yet released) release date for a media, but do not want to search the web everyday for updates
Our app automatically search for updated release dates for the user’s favorites media
Users who wish to purchase a limited, upcoming media and must act quick upon release date or else the media will be sold out
Users may choose to be notified a certain amount of time before the actual release date to better prepare for the product release 



#### Target users

Persona 1: 
Student who struggles to keep up with the latest film and anime releases. The user has limited time and wants to save money & keep up with as many releases they might be interested in as possible.
	The user can choose which series/films they are interested in and have the app remind them about it. They will also be able to get suggestions on movies/series they might be interested in (where other users who like the same things also liked), and also be able to connect with those users. 

Persona 2:
Music fanatic who loves going to concerts and attending album release parties. The user wants to be able to follow their favourite musicians and be notified whenever they announce concerts in town/new albums. 
	The user can follow/favourite artists, and the app will notify the user whenever there is a new concert announced. The user can then choose to follow specific concerts that they might go to, and be redirected to ticket sites they can purchase tickets from. 

Persona 3:
Sneakerhead who is looking to get all the latest shoe releases and wants to be first in line. The user wants to be first in line at any cost, and for lots of releases.
The user can set multiple notifications to be reminded of releases, and they can see all releases they are following in a nice calendar form, or a list form. They are also able to search by company/location/date to find the specific release they are looking for.  


#### Q3: Why would your users choose your product? What are they using today to solve their problem/need?


S@turn aggregates all the release date information that users need in one easy to access, convenient place. Users would want to choose our product because it organizes and displays only relevant information pertaining to event/release dates that they need to see.  This product goes beyond the simple scheduling apps and calendars that users currently rely on by simplifying the process of searching for and juggling several changing release dates by independently tracking the drop dates for various forms of media/products even as those dates shift around throughout the calendar year. Furthermore, S@turn allows users’ to discover new events/media/products that they might be interested in based on trending topics. Users will never  accidentally miss out on concert tickets, movie releases and product sales. Discovering new content before it release as soon as possible.


#### Key decisions and/or insights
Which database to use: Postgres
Free and open-source
Knowledge of Postgres from other courses
Alternatives:
MySQL: not free
Use of server
Decided: Yes
Arguments:
Not necessary since we could directly access database
For small amount of users, a server is not necessary
More implementation = more work
The areas of media we should include in our app
Decided: Movies, anime, games, TicketMaster (shows, concerts)
Alternatives: 
Shoes: no appropriate API
Console: large gap between release dates, not appealing to our usage
Manga: too much data, other specialized apps on market already 
Must ensure appropriate APIs are available for scraping 
Ensure our areas appeal to a wide group of people (larger target audiences)
Could hinder our workload if too much areas are included
Database structure depend upon areas we decide to implement
Android version
Decided: Marshmallow 
Alternatives: other versions
Arguments:
Don’t need the latest features
Increase compatibility across android devices

