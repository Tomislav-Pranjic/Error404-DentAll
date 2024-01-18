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
    @Column(name = "datum_rodenja")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "smj_pref", referencedColumnName = "tip_id")
    private AccommodationType accommodationPreference;

    public MedUser() {
        this.remoteUserId = null;
        this.name = null;
        this.surname = null;
        this.email = null;
        this.phoneNumber = null;
        this.dateOfBirth = null;
        this.accommodationPreference = null;
    }

    public MedUser(String name, String surname, String email, String phoneNumber, AccommodationType accommodationPreference, Date dateOfBirth) {
        this.remoteUserId = null;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accommodationPreference = accommodationPreference;
        this.dateOfBirth = dateOfBirth;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "MedUser{" +
                "localUserId=" + localUserId +
                ", remoteUserId=" + remoteUserId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accommodationPreference=" + accommodationPreference +
                '}';
    }
}
