import model.Passenger;
import model.Trip;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class TripTest {

    @Test
    public void createTrip() throws IOException {

        Trip trip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();

        Trip tripResult = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .post("http://localhost:8080/trip/createTrip")
                .as(Trip.class);
            System.out.println(tripResult);

    }

    @Test
    public void getTrip() {
        given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .get("http://localhost:8080/trip/getTrip/1")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(1));
                System.out.println("getTrip");
    }

    @Test
    public void updateTrip() throws IOException {

        Trip trip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();

        Trip tripResult = given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .body(trip)
                .when()
                .put("http://localhost:8080/trip/putTrip/")
                .as(Trip.class);
            System.out.println(tripResult);


    }

    @Test
    public void deleteTrip() {
        given()
                .log().all(true)
                .contentType("application/json")
                .accept("application/json")
                .when()
                .delete("http://localhost:8080/trip/deleteTrip/7")
                .then()
                .assertThat()
                .statusCode(200);
    }

}


