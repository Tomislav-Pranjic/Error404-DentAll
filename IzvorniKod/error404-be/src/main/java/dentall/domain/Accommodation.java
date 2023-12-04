package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;


@Entity(name = "SMJESTAJ")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smjestaj_id")
    private Long accommodationId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "smjestaj_tip", referencedColumnName = "tip_id")
    private AccommodationType apartmentType;

    @NotNull
    @Column(name = "br_zvijezdica")
    private Integer noOfStars;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adr_id", referencedColumnName = "adresa_id")
    private Address address;

    @NotNull
    @Column(name = "vlasnistvo")
    private Boolean isOwner;

    @NotNull
    @Column(name = "dostupno_do")
    private Date availableUntil;

    public Accommodation () {
        this.accommodationId = null;
        this.apartmentType = null;
        this.noOfStars = null;
        this.address = null;
        this.isOwner = null;
        this.availableUntil = null;
    }

    public Accommodation (Long accomodationId, AccommodationType apartmentType, Integer noOfStars, Address address, Boolean isOwner, Date availableUntil) {
        this.accommodationId = accomodationId;
        this.apartmentType = apartmentType;
        this.noOfStars = noOfStars;
        this.address = address;
        this.isOwner = isOwner;
        this.availableUntil = availableUntil;
    }

    public Long getAccomodationId () {
        return accommodationId;
    }

    public void setAccomodationId (Long accomodationId) {
        this.accommodationId = accomodationId;
    }

    public AccommodationType getApartmentType () {
        return apartmentType;
    }

    public void setApartmentType (AccommodationType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public Integer getNoOfStars () {
        return noOfStars;
    }

    public void setNoOfStars (Integer noOfStars) {
        this.noOfStars = noOfStars;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner (Boolean isOwner) {
        this.isOwner = isOwner;
    }

    public Date getAvailableUntil () {
        return availableUntil;
    }

    public void setAvailableUntil (Date availableUntil) {
        this.availableUntil = availableUntil;
    }
}
