package dentall.rest.dto;

public class TreatmentInfoPatchDTO {
    private String arrivalDate;
    private String departureDate;
    private Long arrivalAddressId;
    private Long departureAddressId;

    public TreatmentInfoPatchDTO() {
    }

    public TreatmentInfoPatchDTO(String arrivalDate, String departureDate, Long arrivalAddressId, Long departureAddressId) {
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalAddressId = arrivalAddressId;
        this.departureAddressId = departureAddressId;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public Long getArrivalAddressId() {
        return arrivalAddressId;
    }

    public Long getDepartureAddressId() {
        return departureAddressId;
    }
}
