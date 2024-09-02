package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Trip {
    private Long id;
    private Long companyId;
    private String plane;
    private String townFrom;
    private String townTo;
    private LocalDateTime timeOut;
    private LocalDateTime timeIn;
    private final List<Passenger> passengerList = new ArrayList<>();

    public static class Builder {
        ObjectMapper objectMapper = new ObjectMapper();

        private final Trip trip;

        public Builder() {
            trip = new Trip();
            objectMapper.registerModule(new JavaTimeModule());
        }

        public Builder withId(Long id) {
            trip.id = id;
            return this;
        }

        public Builder withPassengers(List<Passenger> passengerList) {
            trip.passengerList.addAll(passengerList);
            Collections.sort(trip.passengerList);
            return this;
        }

        public Builder withPlane(String plane) {
            trip.plane = plane;
            return this;
        }

        public Builder withTownTo(String townTo) {
            trip.townTo = townTo;
            return this;
        }

        public Builder withRandomMainInfo(long companyId) {
            trip.companyId = companyId;
            trip.plane = RandomStringUtils.random(5, true, false);
            trip.townFrom = RandomStringUtils.random(10, true, false);
            trip.townTo = RandomStringUtils.random(10, true, false);
            trip.timeOut = LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS);
            trip.timeIn = LocalDateTime.now().plusDays(5).plusHours(5).truncatedTo(ChronoUnit.SECONDS);
            return this;
        }

        public Trip build() {
            return trip;
        }

    }
}