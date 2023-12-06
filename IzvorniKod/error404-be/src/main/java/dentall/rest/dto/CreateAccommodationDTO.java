package dentall.rest.dto;

public class CreateAccommodationDTO {
    private Long typeId;

    private Integer stars;

    private Long addressId;

    private Boolean isOwner;

    private String availableUntil;

    public Long getTypeId() {
        return typeId;
    }

    public Integer getStars() {
        return stars;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public String getAvailableUntil() {
        return availableUntil;
    }
}
