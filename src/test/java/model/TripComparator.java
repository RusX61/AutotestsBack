package model;

import dictionaries.IComparator;
import lombok.AllArgsConstructor;
import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class TripComparator implements IComparator {
    protected final Trip actual, expected;

    public void compare() {
        if (actual == null || expected == null) {
            throw new NullPointerException("Actual or expected trip is null");
        }
        assertThat(actual.getCompanyId()).isEqualTo(expected.getCompanyId());
        assertThat(actual.getPlane()).isEqualTo(expected.getPlane());
        assertThat(actual.getTownFrom()).isEqualTo(expected.getTownFrom());
        assertThat(actual.getTownTo()).isEqualTo(expected.getTownTo());
        assertThat(actual.getTimeOut()).isEqualTo(expected.getTimeOut());
        assertThat(actual.getTimeIn()).isEqualTo(expected.getTimeIn());
        assertThat(actual.getPassengerList()).hasSameSizeAs(expected.getPassengerList());
        for (int i = 0; i < actual.getPassengerList().size(); i++) {
            Passenger actualPassenger = actual.getPassengerList().get(i);
            Passenger expectedPassenger = expected.getPassengerList().get(i);
            assertThat(actualPassenger.getFirstName()).isEqualTo(expectedPassenger.getFirstName());
            assertThat(actualPassenger.getMiddleName()).isEqualTo(expectedPassenger.getMiddleName());
            assertThat(actualPassenger.getLastName()).isEqualTo(expectedPassenger.getLastName());
        }
    }
    public void compareTrip() {
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id", "passengerList.id")
                .isEqualTo(expected);
    }
}