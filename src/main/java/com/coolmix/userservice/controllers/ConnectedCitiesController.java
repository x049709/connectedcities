package com.coolmix.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coolmix.userservice.utils.CityListChecker;

/*Example:
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
*/


@RestController
@RequestMapping(path="/", produces="application/json")
public class ConnectedCitiesController {
	
	public ConnectedCitiesController() {}
	
		@GetMapping("/connected")    
		public String checkIfConnected(
		    @RequestParam(value="origin", required=true) String orig,
		    @RequestParam(value="destination", required=false) String dest)
		{			
			CityListChecker cityListChecker = CityListChecker.getInstance();
			return cityListChecker.checkIfConnected(orig, dest) ?  "yes" : "no";
		}
	
}
