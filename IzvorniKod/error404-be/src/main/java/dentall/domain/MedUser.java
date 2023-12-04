package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;


@Entity(name = "KORISNIK")
public class MedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnik_local_id")
    private Long localUserId;

    @Column(name = "korisnik_remote_id", unique = true)
    private Long remoteUserId;

    @NotNull
    @Column(name = "ime")
    @Size(max = 32)
    private String name;

    @NotNull
    @Column(name = "prezime")
    @Size(max = 32)
    private String surname;

    @NotNull
    @Column(name = "e_mail")
    @Size(max = 64)
    private String email;

    @NotNull
    @Column(name = "broj_mobitela")
    @Size(max = 16)
    private String phoneNumber;

    @NotNull
    @Column(name = "dat_dol")
    private Date arrivalDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "adr_dol", referencedColumnName = "adresa_id")
    private Address arrivalAddress;

    @NotNull
    @Column(name = "dat_odl")
    private Date departureDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "adr_odl", referencedColumnName = "adresa_id")
    private Address departureAddress;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "smj_pref", referencedColumnName = "tip_id")
    private AccommodationType accommodationPreference;

    @ManyToOne
    @JoinColumn(name = "vozac_dovozi", referencedColumnName = "vozac_id")
    private Driver arrivalDriver;

    @ManyToOne
    @JoinColumn(name = "vozac_odvozi", referencedColumnName = "vozac_id")
    private Driver departureDriver;

    @ManyToOne
    @JoinColumn(name = "smjestaj_id", referencedColumnName = "smjestaj_id")
    private Accommodation accommodation;

    public MedUser() {
        this.remoteUserId = null;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.phoneNumber = null;
        this.arrivalDate = null;
        this.arrivalAddress = null;
        this.departureDate = null;
        this.departureAddress = null;
        this.accommodationPreference = null;
        this.arrivalDriver = null;
        this.departureDriver = null;
        this.accommodation = null;
    }

    public MedUser(String name, String surname, String email, String phoneNumber, Date arrivalDate, Address arrivalAddress, Date departureDate, Address departureAddress, AccommodationType accommodationPreference) {
        this.remoteUserId = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.arrivalDate = arrivalDate;
        this.arrivalAddress = arrivalAddress;
        this.departureDate = departureDate;
        this.departureAddress = departureAddress;
        this.accommodationPreference = accommodationPreference;
        this.arrivalDriver = null;
        this.departureDriver = null;
        this.accommodation = null;
    }

    public Long getLocalUserId () {
        return localUserId;
    }

    public Long getRemoteUserId () {
        return remoteUserId;
    }

    public void setRemoteUserId (Long remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getSurname () {
        return surname;
    }

    public void setSurname (String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getArrivalDate () {
        return arrivalDate;
    }

    public void setArrivalDate (Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Address getArrivalAddress() {
        return arrivalAddress;
    }

    public void setArrivalAddress(Address arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
    }

    public Date getDepartureDate () {
        return departureDate;
    }

    public void setDepartureDate (Date departureDate) {
        this.departureDate = departureDate;
    }

    public AccommodationType getAccommodationPreference() {
        return accommodationPreference;
    }

    public void setAccommodationPreference(AccommodationType accomodationPreference) {
        this.accommodationPreference = accomodationPreference;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getDepartureAddress() {
        return departureAddress;
    }

    public void setDepartureAddress(Address departureAddress) {
        this.departureAddress = departureAddress;
    }

    public Driver getArrivalDriver() {
        return arrivalDriver;
    }

    public void setArrivalDriver(Driver arrivalDriver) {
        this.arrivalDriver = arrivalDriver;
    }

    public Driver getDepartureDriver() {
        return departureDriver;
    }

    public void setDepartureDriver(Driver departureDriver) {
        this.departureDriver = departureDriver;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }
}
