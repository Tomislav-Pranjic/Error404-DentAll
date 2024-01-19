package dentall.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

class AccommodationTest {

    Address adresa;
    Date datum;
    AccommodationType accommodationType;
    Accommodation accommodationEmpty;
    Accommodation accommodationTest;

    @BeforeEach
    @DisplayName("Setting up info")
    void setUp(){
        accommodationEmpty = new Accommodation();
        adresa = new Address("Zagreb", "Unska Ulica", 23);
        datum = new Date(0);
        accommodationType = new AccommodationType("stan", 200);
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
        assertEquals(accommodationType, accommodationTest.getApartmentType(), "Getter should return correct type.");
        assertEquals(adresa, accommodationTest.getAddress(), "Getter should return correct adress");
        assertEquals(datum, accommodationTest.getAvailableUntil(), "Getter should return correct date");
        assertEquals(3, accommodationTest.getNoOfStars(), "Getter should return correct number of stars");
        assertEquals(5, accommodationTest.getNoOfBeds(), "Getter should return correct number of beds");
        assertEquals(true, accommodationTest.getOwner(), "Getter should return correct owner");
    }

}