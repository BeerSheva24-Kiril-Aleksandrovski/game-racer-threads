# HW 43 Definition
## The same project structure
## Updating code
### Output: Instead of printing out only numer of the winner thread the applications should outline a results table
table should include following: <br>
- place <br>
- racer number<br>
- running time in milliseconds
### Notes:
- all threads have the same start time (hint: property of Race)
- each thread has its own finish time (hint: property of Racer)

# HW 42 Definition
## Figure out a solution for defining racer-winner based on the code of CW 42 (Just CW used AtomicLong you may use AtomicInteger)
### racer-winner is the thread finishing all iterations first
## Main class as a controller see TODO comments
### It actually prints out some message like "Congrats to Racer (<number> - winner)"
## Race class
### comprises of the game parameters (see TODO comments)
## Racer class extending Thread
### Thread running iterations with some random sleeping in each iteration (see TODO comments)
### Does something for defining the winner (Challenge for your thinking)