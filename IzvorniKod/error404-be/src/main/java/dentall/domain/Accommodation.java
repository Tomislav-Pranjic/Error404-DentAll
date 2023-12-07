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
    @Column(name = "br_zvjezdica")
    private Integer noOfStars;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adr_id", referencedColumnName = "adresa_id")
    private Address address;

    @NotNull
    @Column(name = "vlasnistvo")
    private Boolean isOwner;    // true - u vlasništvu, false - iznajmljeno

    @Column(name = "dostupno_do")
    private Date availableUntil;

    @NotNull
    @Column(name = "br_kreveta")
    private Integer noOfBeds;

    public Accommodation () {
        this.apartmentType = null;
        this.noOfStars = null;
        this.address = null;
        this.isOwner = null;
        this.availableUntil = null;
        this.noOfBeds = null;
    }

    public Accommodation (AccommodationType apartmentType, Integer noOfStars, Address address, Boolean isOwner, Date availableUntil, Integer noOfBeds){
        this.apartmentType = apartmentType;
        this.noOfStars = noOfStars;
        this.address = address;
        this.isOwner = isOwner;
        this.availableUntil = availableUntil;
        this.noOfBeds = noOfBeds;
    }

    public Long getAccomodationId () {
        return accommodationId;
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

    public Integer getNoOfBeds () {
        return noOfBeds;
    }

    public void setNoOfBeds (Integer noOfBeds) {
        this.noOfBeds = noOfBeds;
    }
}
