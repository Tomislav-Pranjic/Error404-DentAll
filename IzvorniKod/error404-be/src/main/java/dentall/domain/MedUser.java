package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


@Entity
public class MedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Date arrivalDate;

    @NotNull
    private String arrivalTown;

    @NotNull
    private Date departureDate;

    @NotNull
    private String accomodationPreference; //nisam siguran kako s ovime?


    public MedUser() {
        this.userId = null;
        this.name = null;
        this.surname = null;
        this.phoneNumber = null;
        this.arrivalDate = null;
        this.arrivalTown = null;
        this.departureDate = null;
        this.accomodationPreference = null;
    }

    public MedUser(Long userId, String name, String surname, String phoneNumber, Date arrivalDate, String arrivalTown, Date departureDate, String accomodationPreference) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.arrivalDate = arrivalDate;
        this.arrivalTown = arrivalTown;
        this.departureDate = departureDate;
        this.accomodationPreference = accomodationPreference;
    }

    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
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

    public String getArrivalTown () {
        return arrivalTown;
    }

    public void setArrivalTown (String arrivalTown) {
        this.arrivalTown = arrivalTown;
    }

    public Date getDepartureDate () {
        return departureDate;
    }

    public void setDepartureDate (Date departureDate) {
        this.departureDate = departureDate;
    }

    public String getAccomodationPreference () {
        return accomodationPreference;
    }

    public void setAccomodationPreference (String accomodationPreference) {
        this.accomodationPreference = accomodationPreference;
    }

}
