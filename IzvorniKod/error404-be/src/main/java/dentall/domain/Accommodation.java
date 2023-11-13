package dentall.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;

    @NotNull
    private String apartmentType;

    @NotNull
    private Integer noOfStars;

    @NotNull
    private String adress;

    @NotNull
    private AccommodationOwner owner;

    @NotNull
    private LocalDate availableUntil; //nekako drugacije ovo?

    public Accommodation () {
        this.accommodationId = null;
        this.apartmentType = null;
        this.noOfStars = null;
        this.adress = null;
        this.owner = null;
        this.availableUntil = null;
    }

    public Accommodation (Long accomodationId, String apartmentType, Integer noOfStars, String adress, AccommodationOwner owner, LocalDate availableUntil) {
        this.accommodationId = accomodationId;
        this.apartmentType = apartmentType;
        this.noOfStars = noOfStars;
        this.adress = adress;
        this.owner = owner;
        this.availableUntil = availableUntil;
    }

    public Long getAccomodationId () {
        return accommodationId;
    }

    public void setAccomodationId (Long accomodationId) {
        this.accommodationId = accomodationId;
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

    public AccommodationOwner getOwner () {
        return owner;
    }

    public void setOwner (AccommodationOwner owner) {
        this.owner = owner;
    }

    public LocalDate getAvailableUntil () {
        return availableUntil;
    }

    public void setAvailableUntil (LocalDate availableUntil) {
        this.availableUntil = availableUntil;
    }
}
