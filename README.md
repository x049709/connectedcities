# Introduction
 CODE CHALLENGE
  
 You’re asked to write a program in Java which determines if two cities are connected.
 Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.
  
 List of roads is available in a file.
 File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.
  
 It will be deployed as a spring-boot app and expose one endpoint:
 http://localhost:8080/connected?origin=city1&destination=city2
  
 Your program should respond with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.
 Any unexpected input should result in a ’no’ response.
  
 Example:
 city.txt content is:
 Boston, New York
 Philadelphia, Newark
 Newark, Boston
 Trenton, Albany
  
 http://localhost:8080/connected?origin=Boston&destination=Newark
 Should return yes
 http://localhost:8080/connected?origin=Boston&destination=Philadelphia
 Should return yes
 http://localhost:8080/connected?origin=Philadelphia&destination=Albany
 Should return no
 
# Software needed

# Building the executable
Run the following maven command at the root of the project directory

   **mvn clean install**

# Running the service

Run the following command from the root

   **java  -jar target/user-service-0.0.1-SNAPSHOT.jar cities.txt**

# Points to consider
1. The logic to determine if cities are connected is not straightforward. I looked around for algorithms I could use, but ended up with my own anyway. 
   I'm sure something exists out there; I just didn't have the time to look and understand what's out there 
2. The file is loaded from the command line. Currently the file is sitting in the root directory. 
3. The program loads the file to a couple of collections. If there are invalid lines, the program just ignores them and loads only the good ones
4. The logic to determine city connections goes like this:
	a. Check if the 'from city' is in the hashmap. If it is, check if the map value is the same as the 'to city'. If it is, the work is done
	b. If not, check if the 'from city' follows a path that eventually leads to the'to city'. This is not straightforward. I ended up employing a progressively shrinking hashset 
	   to stop the recursion from running away. The recursion stops when all possible paths are exhausted or if the 'to city' is found.
    
