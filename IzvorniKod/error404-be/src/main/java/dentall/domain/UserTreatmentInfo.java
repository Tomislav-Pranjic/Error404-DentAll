package dentall.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "TRETMAN_INFO")
public class UserTreatmentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tretman_id")
    private Long treatment_id;

    @Column(name = "dat_dol")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "dat_odl")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "adr_dol", referencedColumnName = "adresa_id")
    private Address arrivalAddress;

    @ManyToOne
    @JoinColumn(name = "adr_odl", referencedColumnName = "adresa_id")
    private Address departureAddress;

    @ManyToOne
    @JoinColumn(name = "vozac_dovozi", referencedColumnName = "vozac_id")
    private Driver arrivalDriver;

    @ManyToOne
    @JoinColumn(name = "vozac_odvozi", referencedColumnName = "vozac_id")
    private Driver departureDriver;

    @ManyToOne
    @JoinColumn(name = "smjestaj_id", referencedColumnName = "smjestaj_id")
    private Accommodation accommodation;

    @NotNull
    @Column(name = "dat_tretmana")
    @Temporal(TemporalType.DATE)
    private Date treatmentDate;

    @NotNull
    @Column(name = "vrijeme_zakljucavanja") // Potrebno za traženje idućih tretmana
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lockDateTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_local_id")
    private MedUser medUser;

    public UserTreatmentInfo(Long treatment_id, Date arrivalDate, Date departureDate, Address arrivalAddress, Address departureAddress, Driver arrivalDriver, Driver departureDriver, Accommodation accommodation, Date treatmentDate, Timestamp lockDateTime, MedUser medUser) {
        this.treatment_id = treatment_id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalAddress = arrivalAddress;
        this.departureAddress = departureAddress;
        this.arrivalDriver = arrivalDriver;
        this.departureDriver = departureDriver;
        this.accommodation = accommodation;
        this.treatmentDate = treatmentDate;
        this.lockDateTime = lockDateTime;
        this.medUser = medUser;
    }

    public UserTreatmentInfo() {
        this.treatment_id = null;
        this.arrivalDate = null;
        this.departureDate = null;
        this.arrivalAddress = null;
        this.departureAddress = null;
        this.arrivalDriver = null;
        this.departureDriver = null;
        this.accommodation = null;
        this.treatmentDate = null;
        this.lockDateTime = null;
        this.medUser = null;
    }

    public UserTreatmentInfo(Date treatmentDate, Timestamp lockDateTime, MedUser medUser) {
        this.treatment_id = null;
        this.arrivalDate = null;
        this.departureDate = null;
        this.arrivalAddress = null;
        this.departureAddress = null;
        this.arrivalDriver = null;
        this.departureDriver = null;
        this.accommodation = null;
        this.treatmentDate = treatmentDate;
        this.lockDateTime = lockDateTime;
        this.medUser = medUser;
    }

    public Long getTreatment_id() {
        return treatment_id;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Address getArrivalAddress() {
        return arrivalAddress;
    }

    public void setArrivalAddress(Address arrivalAddress) {
        this.arrivalAddress = arrivalAddress;
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

    public Date getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(Date treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public Timestamp getLockDateTime() {
        return lockDateTime;
    }

    public void setLockDateTime(Timestamp lockTime) {
        this.lockDateTime = lockTime;
    }

    public MedUser getMedUser() {
        return medUser;
    }

    public void setMedUser(MedUser medUser) {
        this.medUser = medUser;
    }
}
