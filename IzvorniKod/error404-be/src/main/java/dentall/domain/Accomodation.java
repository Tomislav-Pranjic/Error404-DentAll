package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Accomodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accomodationId;

    @NotNull
    private String apartmentType;

    @NotNull
    private Integer noOfStars;

    @NotNull
    private String adress;

    @NotNull
    private AccomodationOwner owner;

    @NotNull
    private LocalDate availableUntil; //nekako drugacije ovo?

    public Accomodation () {
        this.accomodationId = null;
        this.apartmentType = null;
        this.noOfStars = null;
        this.adress = null;
        this.owner = null;
        this.availableUntil = null;
    }

    public Accomodation (Long accomodationId, String apartmentType, Integer noOfStars, String adress, AccomodationOwner owner, LocalDate availableUntil) {
        this.accomodationId = accomodationId;
        this.apartmentType = apartmentType;
        this.noOfStars = noOfStars;
        this.adress = adress;
        this.owner = owner;
        this.availableUntil = availableUntil;
    }

    public Long getAccomodationId () {
        return accomodationId;
    }

    public void setAccomodationId (Long accomodationId) {
        this.accomodationId = accomodationId;
    }

    public String getApartmentType () {
        return apartmentType;
    }

    public void setApartmentType (String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Integer getNoOfStars () {
        return noOfStars;
    }

    public void setNoOfStars (Integer noOfStars) {
        this.noOfStars = noOfStars;
    }

    public String getAdress () {
        return adress;
    }

    public void setAdress (String adress) {
        this.adress = adress;
    }

    public AccomodationOwner getOwner () {
        return owner;
    }

    public void setOwner (AccomodationOwner owner) {
        this.owner = owner;
    }

    public LocalDate getAvailableUntil () {
        return availableUntil;
    }

    public void setAvailableUntil (LocalDate availableUntil) {
        this.availableUntil = availableUntil;
    }
}
