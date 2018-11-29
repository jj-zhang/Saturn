# Saturn / Group 1

## Iteration 3 - Review & Retrospect

 * When: November 26 
 * Where: Online

## Process - Reflection

#### Decisions that turned out well

List process-related (i.e. team organization) decisions that, in retrospect, turned out to be successful.


 * 2 - 4 decisions.
 * Ordered from most to least important.
 * Explain why (i.e. give a supporting argument) you consider a decision to be successful.
 * Feel free to refer/link to process artifact(s).
 
 * Using API for scraping instead of using jSoup and scrape from Wikipedia
   * Much less code in python, easy to call, easy to access the information we want
   * Using jSoup requires the code to be inside the app - not what we want
   * Please see our scraper scripts in the scraper folder
 * To meet up accordingly in terms of different areas of the project (eg. scraper, frontend, etc.) and do pair coding
   * Speeds up work process (more focused)
   * Members know what is going on exactly

#### Decisions that did not turn out as well as we hoped

List process-related (i.e. team organization) decisions that, in retrospect, were not as successful as you thought they would be.

 * 2 - 4 decisions.
 * Ordered from most to least important.
 * Feel free to refer/link to process artifact(s).
 
 * Using Elephant PostgresSQL as our database
   * We found out that the integration of database with the Android app requires any internet connection needs to be put in background (work that we did not expect)
   * Should have used cloud service such as AWS, GCP, etc.
 * Should have organized database to only contain id and type of media in order to fit in more information
   * Currently scrape a lot of information (takes a lot of space)
   * Could have stored id and type, then we can call the API within the app (using the type to query the specific API with id) to get the most updated information
   


#### Planned changes

List any process-related changes you are planning to make (if there are any)
 
 * No changes


## Product - Review

Goals from Planning

 * Integrate backend with frontend fully and make sure databases are connected properly
   * Mainly, frontend needs to call backend functions to get/change information from/to the database
 * Develop the notifications feature
   * Notify the user if a upcoming release date is approaching
 * Clean up any leftover UI work
   * Notification feature, some spelling mistakes
 * Finish off all scrapers
   * Finish all scrapers (movie/TV shows, anime, games, and concerts) using various APIs
   * Python scripts

#### Goals and/or tasks that were met/completed:

 * Integration between backend and frontend as well as final work on the database was completed, as evidenced by our app / issues.

 * Notification Functionality works on a basic level.

 * Finish off all scrapers
   * Please see our python scripts in scrapers folder
 


 * From most to least important.
 * Refer/link to artifact(s) that show that a goal/task was met/completed.
 * If a goal/task was not part of the original iteration plan, please mention it.

#### Goals and/or tasks that were planned but not met/completed:

 * From most to least important.
 * For each goal/task, explain why it was not met/completed.      
   e.g. Did you change your mind, or did you just not get to it yet?

## Meeting Highlights

Going into the next iteration, our main insights are:

 * 2 - 4 items
 * Short (no more than one short paragraph per item)
 * High-level concepts that should guide your work for the next iteration.
 * These concepts should help you decide on where to focus your efforts.
 * Can be related to product and/or process.
 
 * Implement additional features to our app (listed in the original product.md)
   * To create a even more functional app for users
   * For example, provide link to stores that have more information about the event
 * Write more tests for backend
   * Ensure the correctness of the methods so the app will run smoothly
