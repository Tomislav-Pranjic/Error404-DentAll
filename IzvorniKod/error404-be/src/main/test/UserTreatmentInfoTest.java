package dentall.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

class UserTreatmentInfoTest {

	UserTreatmentInfo userInfoDef;
	UserTreatmentInfo userInfoTestOne;
	UserTreatmentInfo userInfoTestToo;
	UserTreatmentInfo userInfoFault;
	Date date;
	Address adresa;
	Driver vozac;
	Accommodation accomodation;
	Timestamp trenutakZakljuc;
	MedUser korisnik;
	
	@BeforeEach
	@DisplayName("Setting up necesery info")
	void setUp(){
		userInfoDef = new UserTreatmentInfo();
		date = new Date();
		adresa = new Adresa("Zagreb", "Savska", 132);
		vrijeme = new Time();
		vozilo = new Vehicle("reg", "Porsche 911", "red", 2);
		vozac = new Driver("Marko", "Popić", "mail@mail.com", "0992583695", vozilo, vrijeme, "NUCP");
		accommodationType = new AccommodationType(stan, 200);
		accomodation = new Accommodation(accommodationType, 3, adresa, true, datum, 5);
		korisnik = new MedUser("Luke", "Skywalker", "lukeskywalker@force.com", "036958721",  accomodation, date);
		trenutakZakljuc = new Timestamp();
		userInfoTestOne = new UserTreatmentInfo(123, date, date, adresa, adresa, vozac, vozac, accomodation, date, trenutakZakljuc, korisnik);
		userInfoTestToo = new UserTreatmentInfo(date, trenutakZakljuc, korisnik);
		userInfoFault = new UserTreatmentInfo();
	}

	@Test
	void getUserInfoDef() {
		assertNull(userInfoDef.getArrivalDate(),"Should be null");
        assertNull(userInfoDef.getDepartureDate(), "Should be null");
        assertNull(userInfoDef.getArrivalAddress(),"Should be null");
        assertNull(userInfoDef.getDepartureAddress(),"Should be null");
        assertNull(userInfoDef.getArrivalDriver(),"Should be null");
        assertNull(userInfoDef.getDepartureDriver(),"Should be null");
        assertNull(userInfoDef.getAccommodation(),"Should be null");
        assertNull(userInfoDef.getArrivalTime(),"Should be null");
        assertNull(userInfoDef.getDepartureTime(),"Should be null");
		assertNull(userInfoDef.getTreatmentDate(),"Should be null");
        assertNull(userInfoDef.getLockDateTime(),"Should be null");
        assertNull(userInfoDef.getMedUser(),"Should be null");
	}

	@Test
	void getUserInfoOne() {
		assertEqual(date ,userInfoTestOne.getArrivalDate(), "Getter should return correct info");
		assertEqual(date ,userInfoTestOne.getDepartureDate(), "Getter should return correct info");
		assertEqual(adresa ,userInfoTestOne.getArrivalAddress(), "Getter should return correct info");
        assertEqual(adresa ,userInfoTestOne.getDepartureAddress(), "Getter should return correct info");
        assertEqual(vozac ,userInfoTestOne.getArrivalDriver(), "Getter should return correct info");
		assertEqual(vozac ,userInfoTestOne.getDepartureDriver(), "Getter should return correct info");
		assertEqual(accomodation ,userInfoTestOne.getAccommodation(), "Getter should return correct info");
        assertEqual(date ,userInfoTestOne.getTreatmentDate(), "Getter should return correct info");
		assertEqual(trenutakZakljuc ,userInfoTestOne.getLockDateTime(), "Getter should return correct info");
        assertEqual(korisnik ,userInfoTestOne.getMedUser(), "Getter should return correct info");
        assertNull(userInfoDef.getArrivalTime(), "Should be null");
        assertNull(userInfoDef.getDepartureTime(), "Should be null");
	}

	@Test
	void getUserInfoToo() {
		assertNull(userInfoTestToo.getArrivalDate(),"Should be null");
        assertNull(userInfoTestToo.getDepartureDate(), "Should be null");
        assertNull(userInfoTestToo.getArrivalAddress(),"Should be null");
        assertNull(userInfoTestToo.getDepartureAddress(),"Should be null");
        assertNull(userInfoTestToo.getArrivalDriver(),"Should be null");
        assertNull(userInfoTestToo.getDepartureDriver(),"Should be null");
        assertNull(userInfoTestToo.getAccommodation(),"Should be null");
        assertNull(userInfoTestToo.getArrivalTime(),"Should be null");
        assertNull(userInfoTestToo.getDepartureTime(),"Should be null");
        assertNull(userInfoTestToo.getArrivalTime(), "Should be null");
        assertNull(userInfoTestToo.getDepartureTime(), "Should be null");
		assertEqual(date, userInfoTestToo.getTreatmentDate(), "Getter should return correct info");
		assertEqual(date, userInfoTestToo.getTreatmentDate(), "Getter should return correct info");
		assertEqual(korisnik, userInfoTestToo.getMedUser(), "Getter should return correct info");
	}

	@Test
	void getUserInfoFault(){
		assertThrows(IllegalArgumentException.class, () -> 
		userInfoFault.setArrivalDate(12));
	}

}
