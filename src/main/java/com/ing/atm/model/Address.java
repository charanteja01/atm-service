package com.ing.atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Address {
	String street;
	String houseNumber;
	String postalCode;
	String city;
	GeoLocation geoLocation;
	
	@JsonCreator
	public Address(@JsonProperty("street") String street,
			@JsonProperty("housenumber") String houseNumber,
			@JsonProperty("postalcode") String postalCode,
			@JsonProperty("city") String city,
			@JsonProperty("geolocation") GeoLocation geoLocation) {
		
		this.street = street;
		this.houseNumber = houseNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.geoLocation = geoLocation;
	}
}