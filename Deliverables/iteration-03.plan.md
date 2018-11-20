# Saturn / Team 01

 > _Note:_ This document is meant to be written during (or shortly after) your initial planning meeting.     
 > It does not really make sense for you to edit this document much (if at all) while working on the project - Instead, at the end of the planning phase, you can refer back to this document and decide which parts of your plan you are happy with and which parts you would like to change.


## Iteration 3

 * Start date: Nov 12
 * End date: Nov 26

## Process

(Optional:) Quick introduction

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
 * Feature Impl / Frontend: Darren
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
 * After each sprint planning meeting, each person makes a to-do list on what they need to get done, and ensure that it is updated

#### Git / GitHub workflow
 * Each person either works on their own branch, or on a new branch stemmed out from the master branch for each feature
 * After a feature is complete, make sure the changes do not break the build, and send in a pull request to master
 * After at least one person has reviewed the changes and approved them, the pull request is merged into master

## Product

#### Goals and tasks
 * Integrate backend with frontend fully and make sure databases are connected properly
 * Develop the notifications feature
 * Clean up any leftover UI work

#### Artifacts
 * Integration code between frontend and backend
 * Screencaps showing the latest changes so that we can compare this iteration's changes with the last
 * Video demo presenting our progress
