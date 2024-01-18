package dentall.rest.dto;

import dentall.domain.Driver;

public class DriverWorkInfoDTO {
    private Driver driver;

    private int numberOfTrips;

    public DriverWorkInfoDTO(Driver driver, int numberOfTrips) {
        this.driver = driver;
        this.numberOfTrips = numberOfTrips;
    }

    public Driver getDriver() {
        return driver;
    }

    public int getNumberOfTrips() {
        return numberOfTrips;
    }
}
