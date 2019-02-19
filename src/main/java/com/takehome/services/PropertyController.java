package com.takehome.services;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.takehome.repository.PropertyRepository;

@RestController
public class PropertyController {
	
	@Autowired
	PropertyRepository propertyRepository;
	
	@RequestMapping(value = "/highestpropertyowner",
			method = RequestMethod.POST,
			consumes  = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE
	)
	public String findHighestPropertyOwner(@RequestBody List<String> home_Ids){
		
		if(home_Ids.isEmpty() && home_Ids.size() == 0)
			return "INPUT ERROR : please provide home ids";
		return propertyRepository.findHighestPropertyOwner(home_Ids);

	}

}
