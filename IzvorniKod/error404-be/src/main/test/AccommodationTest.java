package dentall.domain.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import dentall.domain.Accommodation;
import dentall.domain.AccommodationType;
import dentall.domain.Address;
import java.util.Date;



class AccommodationTest {

	Address adresa;
	Date datum;
	AccommodationType accommodationType;
	Accommodation accommodationEmpty;
	Accommodation accommodationTest;
	
	@BeforeEach
	@DisplayName("Setting up info")
	void setUp(){
		accommodation_Empty = new Accommodation();
		adresa = new Address("Zagreb", "Unska Ulica", 23);
		datum = new Date();
		accommodationType = new AccommodationType(stan, 200);
		accommodationTest = new Accommodation(accommodationType, 3, adresa, true, datum, 5);
	}

	@Test
	@DisplayName("Getting default apartment info")
	void testGetDefault() {
		assertNull(accommodationEmpty.getApartmentType(), "Default appartment type should be null");
		assertNull(accommodationEmpty.getAddress(), "Default address should be null");
		assertNull(accommodationEmpty.getAvailableUntil(), "Default available date should be null");
		assertNull(accommodationEmpty.getNoOfStars(), "Default number of stars should be null");
		assertNull(accommodationEmpty.getNoOfBeds(), "Default number of beds should be null");
		assertNull(accommodationEmpty.getOwner(), "Default owner should be null");
	}

	@Test
	@DisplayName("Getting correct apartment info")
	void testGetCorrect() {
		assertEqual(accommodationType, accommodationTest.getApartmentType(), "Getter should return correct type.");
		assertEqual(adresa, accommodationTest.getAddress(), "Getter should return correct adress");
		assertEqual(datum, accommodationTest.getAvailableUntil(), "Getter should return correct date");
		assertEqual(3, accommodationTest.getNoOfStars(), "Getter should return correct number of stars");
		assertEqual(5, accommodationTest.getNoOfBeds(), "Getter should return correct number of beds");
		assertEqual(true, accommodationTest.getOwner(), "Getter should return correct owner");
	}

}
