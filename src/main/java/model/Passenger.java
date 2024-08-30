package model;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Passenger {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;

    public static class Builder {
        private final Passenger passenger;

        public Builder() {
            passenger = new Passenger();
        }

        public Builder withId(Long id) {
            return this;
        }

        public Builder withRandomCompletely() {
            passenger.firstName = RandomStringUtils.random(5, true, false);
            passenger.middleName = RandomStringUtils.random(5, true, false);
            passenger.lastName = RandomStringUtils.random(5, true, false);
            return this;
        }

        public Passenger build() {
            return passenger;
        }
    }
}