package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Transport { //mozda neki bolji naziv
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportId;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String vehicleType;

    @NotNull
    private Integer capacity;

    @NotNull
    private String registration;

    //sto s radnim vremenom?


    public Transport () {
        this.transportId = null;
        this.name = null;
        this.surname = null;
        this.phoneNumber = null;
        this.vehicleType = null;
        this.capacity = null;
        this.registration = null;
    }

    public Transport (Long transportId, String name, String surname, String phoneNumber, String vehicleType, Integer capacity, String registration) {
        this.transportId = transportId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.vehicleType = vehicleType;
        this.capacity = capacity;
        this.registration = registration;
    }

    public Long getTransportId () {
        return transportId;
    }

    public void setTransportId (Long transportId) {
        this.transportId = transportId;
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

    public String getVehicleType () {
        return vehicleType;
    }

    public void setVehicleType (String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getCapacity () {
        return capacity;
    }

    public void setCapacity (Integer capacity) {
        this.capacity = capacity;
    }

    public String getRegistration () {
        return registration;
    }

    public void setRegistration (String registration) {
        this.registration = registration;
    }

}
