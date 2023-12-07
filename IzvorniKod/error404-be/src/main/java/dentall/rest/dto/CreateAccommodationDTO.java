package dentall.rest.dto;

public class CreateAccommodationDTO {
    private Long typeId;

    private Integer stars;

    private Long addressId;

    private String ownership;

    private String availableUntil;

    private Integer noOfBeds;

    public CreateAccommodationDTO(Long typeId, Integer stars, Long addressId, String ownership, String availableUntil, Integer noOfBeds){
        this.typeId = typeId;
        this.stars = stars;
        this.addressId = addressId;
        this.ownership = ownership;
        this.availableUntil = availableUntil;
        this.noOfBeds = noOfBeds;
    }

    public Long getTypeId() {
        return typeId;
    }

    public Integer getStars() {
        return stars;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getOwnership() {
        return ownership;
    }

    public String getAvailableUntil() {
        return availableUntil;
    }

    public Integer getNoOfBeds() {
        return noOfBeds;
    }

    @Override
    public String toString() {
        return "CreateAccommodationDTO{" +
                "typeId=" + typeId +
                ", stars=" + stars +
                ", addressId=" + addressId +
                ", ownership='" + ownership + '\'' +
                ", availableUntil='" + availableUntil + '\'' +
                ", noOfBeds=" + noOfBeds +
                '}';
    }
}
