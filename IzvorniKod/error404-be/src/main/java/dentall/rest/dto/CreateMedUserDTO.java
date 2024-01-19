package dentall.rest.dto;

public class CreateMedUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long accTypePrefId;

    private String dateOfBirth;

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

    public Long getAccTypePrefId() {
        return accTypePrefId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
