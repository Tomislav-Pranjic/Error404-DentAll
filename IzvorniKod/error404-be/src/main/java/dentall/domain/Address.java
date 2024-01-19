package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "ADRESA")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresa_id")
    private Long addressId;

    @NotNull
    @Column(name = "mjesto")
    private String city;

    @NotNull
    @Column(name = "ulica")
    private String street;

    @NotNull
    @Column(name = "broj")
    private Integer number;

    public Address() {
        this.city = null;
        this.street = null;
        this.number = null;
    }

    public Address(String city, String street, Integer number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return  city +
                ", " + street +
                " " + number
                ;
    }
}
