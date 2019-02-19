package com.takehome.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.takehome.domain.Property;

@Repository
public class PropertyRepository {

	public String findHighestPropertyOwner(List<String> home_Ids) {

		int propertyOwnerValue = 0;
		String ownerName = "";
		Property property = null;
		
		RestTemplate restTemplate = new RestTemplate();
		
		// loop through all the input home ids and get the property detail
		for(String homeId : home_Ids){
			try {
				property = restTemplate.getForObject("https://webservice-takehome-1.herokuapp.com/property?property_id="+homeId, Property.class);

			} catch (Exception e) {
				// log the exception and continue to the next request
				continue;
			}
			//System.out.println(property);
			if(property.getValue() > propertyOwnerValue && property.getAddress().getState().equalsIgnoreCase("VA")){
				propertyOwnerValue = property.getValue();
				ownerName = property.getOwner();
			}
		}
		return ownerName;
	}

}
