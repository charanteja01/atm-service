package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeoLocation {
	String latitude;
	String longitude;
	
	@JsonCreator
	public GeoLocation(@JsonProperty("lat") String latitude,
			@JsonProperty("lng") String longitude) {
		
		this.latitude = latitude;
		this.longitude = longitude;
				
	}
	

}
