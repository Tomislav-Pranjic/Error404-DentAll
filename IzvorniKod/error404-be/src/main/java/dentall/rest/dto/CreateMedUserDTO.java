package dentall.rest.dto;

public class CreateMedUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String arrDate;
    private Long arrAddressId;
    private String depDate;
    private Long depAddressId;
    private Long accTypePrefId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getArrDate() {
        return arrDate;
    }

    public Long getArrAddressId() {
        return arrAddressId;
    }

    public String getDepDate() {
        return depDate;
    }

    public Long getDepAddressId() {
        return depAddressId;
    }

    public Long getAccTypePrefId() {
        return accTypePrefId;
    }
}
