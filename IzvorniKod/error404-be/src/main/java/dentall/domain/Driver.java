package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Time;


@Entity(name = "VOZAC")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vozac_id")
    private Long driverId;

    @NotNull
    @Column(name = "ime")
    @Size(max = 16)
    private String name;

    @NotNull
    @Column(name = "prezime")
    @Size(max = 16)
    private String surname;

    @NotNull
    @Column(name = "e_mail")
    @Size(max = 64)
    private String email;

    @NotNull
    @Column(name = "broj_mobitela")
    @Size(max = 10)
    private String phoneNumber;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reg_vozila", referencedColumnName = "reg_vozila")
    private Vehicle vehicle;

    @NotNull
    @Column(name = "poc_rad_vrij")
    private Time workStartTime;

    @NotNull
    @Column(name = "radni_dani")
    @Size(max = 7)
    private String workingDays;
    // N - Nedjelja, P - Ponedjeljak, U - Utorak, S - Srijeda, C - Četvrtak, E - Petak, B - Subota

    public Driver() {
        this.name = null;
        this.surname = null;
        this.email = null;
        this.phoneNumber = null;
        this.vehicle = null;
        this.workStartTime = null;
        this.workingDays = null;
    }

    public Driver(String name, String surname, String email, String phoneNumber, Vehicle vehicle, Time workStartTime, String workingDays) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.vehicle = vehicle;
        this.workStartTime = workStartTime;
        this.workingDays = workingDays;
    }

    public Long getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicleType) {
        this.vehicle = vehicleType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Time getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(Time workStartTime) {
        this.workStartTime = workStartTime;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }

    @Override
    public String toString() {
        return name + " " + surname +
                ", E-mail: " + email +
                ", Phone Number: " + phoneNumber +
                " " + vehicle
                ;
    }
}
