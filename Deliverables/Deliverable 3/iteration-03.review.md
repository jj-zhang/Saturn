# Saturn / Group 1

## Iteration 3 - Review & Retrospect

 * When: November 26 
 * Where: Online

## Process - Reflection

#### Decisions that turned out well
 
 * Using API for scraping instead of using jSoup and scrape from Wikipedia
   * Much less code in python, easy to call, easy to access the information we want
   * Using jSoup requires the code to be inside the app - not what we want
   * Please see our scraper scripts in the scraper folder
   
 * To meet up accordingly in terms of different areas of the project (eg. scraper, frontend, etc.) and do pair coding
   * Speeds up work process (more focused)
   * Members know what is going on exactly by getting instant replies from another member
   * Easier to discuss minor details
   
 * Refactoring and Commenting Code Extensively 
   * Code is significantly cleaner
   * Team was able to efficiently onboard a new member due to the fairly high quality documentation

#### Decisions that did not turn out as well as we hoped
 
 * Using Elephant PostgresSQL as our database
   * We found out that the integration of database with the Android app requires any internet connection needs to be put in background (work that we did not expect)
   * Should have used cloud service such as AWS, GCP, etc.
   * Still works well enough for our purposes but, could be further optimized
   
 * Should have organized database to only contain id and type of media in order to fit in more information
   * Currently, our database only has 10 mb
   * Currently scrape a lot of information (takes a lot of space)
   * Could have stored id and type, then we can call the API within the app (using the type to query the specific API with id) to get the most updated information
   
 * Current Database Schema is Weak
   * Schema is sufficient for small scale operation but if the project were to continue would need to be reworked and broken up into more tables for greater efficiency and ease of use
   
 * Processes were Abused towards the end of the Cycle  
   * As integration work picked up steam important procedures such as rebasing were not done as they should have been
   * Somewhat necessitated due to some friction created by processes

#### Planned changes

 * If anything the most fundamental thing the team needs to reform on is better organization. 
   * Stricter following prescribed process / workflow.
   * Better documenting code *from the outset*
   * Tighter adherence to marking issues etc.
   * Improving communication further particularily with regard to work done at night etc.

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
   * Please look at the screenshot images in the Screenshots and Images folder
   * Pull request #151

 * Finish off all scrapers
   * Please see our python scripts in scrapers folder
   * Working example of scraper at work inside the video
 

#### Goals and/or tasks that were planned but not met/completed:

 * The artist following feature was unfortunately scrapped as we did not have enough time to finish the feature. However this was supposed to be an add-on functionality that would have been nice to have but not necessary, so it was not a huge blow to our product. 

## Meeting Highlights

Going into the next iteration, our main insights are:
 
 * Implement additional features to our app (listed in the original product.md)
   * To create a even more functional app for users
   * For example, provide link to stores that have more information about the event
 * Write more tests for backend
   * Ensure the correctness of the methods so the app will run smoothly


Note: Please note that Tiffany-Ng and Prynciss Ng are the same person (when looking at commits), the commits by'Prynciss Ng' and another 'Tiffany-Ng' are done by the same person but on a different computer.
