# Saturn / Team 01

## Iteration 3

 * Start date: Nov 12
 * End date: Nov 26

## Process

#### Changes from previous iteration

List the most significant changes you made to your process (if any).

 * We've decided to forego using the frontend-master and backend-master branches, since we are in the process of integrating the frontend and the backend now. This will be much easier to organize and to keep track of, and each side will be able to get changes much easier now. 

#### Roles & responsibilities
 * Backend / Scraping: Tim, Tiffany
   * Set up of the backend databases as well as JDBC interface with the database
   * Implementation of the scrapers in order to get data for the database
 * Frontend: Ellen, Ray
   * Frontend design & implementation, including application setup
   * Integration of backend database methods into frontend
 * Backend / QA: Reece
   * Set up of the backend databases as well as JDBC interface with the database
   * Writing tests for the backend classes
 * Feature Impl / Frontend / Jack of All Trades: Darren
   * Implementation of the notifications feature
   * Integration of frontend to backend

#### Events
 * Weekly sprint planning/review
   * During tutorial every Monday night
   * Review of planned tasks that was to be done in the prior week
   * Go over the tasks still left to be done and plan who's doing what
 * Virtual discussion
   * Anytime online in the group chat
   * Discuss any obstacles encountered during work
   * Propose solutions
 * Daily check-ups
   * Online in the group chat
   * Everyone who's done work in the past day informs the group on their progress and what they plan to do next

#### Artifacts
 * Project board on GitHub keeps track of project completion status
   * Any issues/work that comes up get turned into an issue on Github and are assigned to specific members
 * After each sprint planning meeting, each person makes a to-do list on what they need to get done, and ensure that it is updated
   * This can be done verbally or recorded initially

#### Git / GitHub workflow
 * Each person either works on their own branch, or on a new branch stemmed out from the master branch for each feature
 * After a feature is complete, make sure the changes do not break the build, and send in a pull request to master
 * After at least one person has reviewed the changes and approved them, the pull request is merged into master 
   * People who review are usually those who are closely related to the changes (eg. scrapers, frontend, backend, etc.)
   * This can be done during the weekly meetings or online, after thoroughly discussing with the appropriate team members (front/backend)
 * We chose this workflow for it is the easiest way to maintain a clean master branch while letting others work on different issues at the same time. Other members can also checkout an issue branch and work the an issue that other member have started to work on. 

## Product

#### Goals and tasks
 * Integrate backend with frontend fully and make sure databases are connected properly
   * Mainly, frontend needs to call backend functions to get/change information from/to the database
 * Develop the notifications feature
   * Notify the user if a upcoming release date is approaching
 * Clean up any leftover UI work
   * Notification feature, some spelling mistakes
 * Finish off all scrapers
   * Finish all scrapers (movie/TV shows, anime, games, and concerts) using various APIs 
   * Python scripts

#### Artifacts
 * Integration code between frontend and backend
   * Important for linking the whole app together
 * Screencaps showing the latest changes so that we can compare this iteration's changes with the last
   * Shows progress throughout this iteration
   * Will show linking between frontend and backend - important for not all members are familiar with both (a chance to see what others have worked on)
 * Video demo presenting our progress
     * Provide context of our project
     * Show progress from last iteration
     * Showcase how our project was implemented and next steps
