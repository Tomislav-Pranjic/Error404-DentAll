package dentall.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class UserTreatmentInfoTest {
    UserTreatmentInfo userInfoDef;
    UserTreatmentInfo userInfoTestOne;
    UserTreatmentInfo userInfoTestToo;
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
        date = new Date(0);
        adresa = new Address("Zagreb", "Savska", 132);
        Time time = new Time(0);
        Vehicle vehicle = new Vehicle("reg", "Porsche 911", "red", 2);
        vozac = new Driver("Marko", "Popić", "mail@mail.com", "0992583695", vehicle, time, "NUCP");
        AccommodationType accommodationType = new AccommodationType("stan", 200);
        accomodation = new Accommodation(accommodationType, 3, adresa, true, date, 5);
        korisnik = new MedUser("Luke", "Skywalker", "lukeskywalker@force.com", "036958721",  accommodationType, date);
        trenutakZakljuc = new Timestamp(0);
        userInfoTestOne = new UserTreatmentInfo(123L, date, date, adresa, adresa, vozac, vozac, accomodation, date, trenutakZakljuc, korisnik);
        userInfoTestToo = new UserTreatmentInfo(date, trenutakZakljuc, korisnik);
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
        assertEquals(date ,userInfoTestOne.getArrivalDate(), "Getter should return correct info");
        assertEquals(date ,userInfoTestOne.getDepartureDate(), "Getter should return correct info");
        assertEquals(adresa ,userInfoTestOne.getArrivalAddress(), "Getter should return correct info");
        assertEquals(adresa ,userInfoTestOne.getDepartureAddress(), "Getter should return correct info");
        assertEquals(vozac ,userInfoTestOne.getArrivalDriver(), "Getter should return correct info");
        assertEquals(vozac ,userInfoTestOne.getDepartureDriver(), "Getter should return correct info");
        assertEquals(accomodation ,userInfoTestOne.getAccommodation(), "Getter should return correct info");
        assertEquals(date ,userInfoTestOne.getTreatmentDate(), "Getter should return correct info");
        assertEquals(trenutakZakljuc ,userInfoTestOne.getLockDateTime(), "Getter should return correct info");
        assertEquals(korisnik ,userInfoTestOne.getMedUser(), "Getter should return correct info");
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
        assertEquals(date, userInfoTestToo.getTreatmentDate(), "Getter should return correct info");
        assertEquals(date, userInfoTestToo.getTreatmentDate(), "Getter should return correct info");
        assertEquals(korisnik, userInfoTestToo.getMedUser(), "Getter should return correct info");
    }

}