import dictionaries.IPathEnum;
import dictionaries.TripPathEnum;
import io.restassured.response.Response;
import model.*;
import org.apache.commons.lang3.RandomUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;


public class TripTest {

    private static Map<IPathEnum, String> serviceDataMap;
    private static Trip createTrip;
    private static Trip putTrip;

    @BeforeClass
    public static void init() {
        serviceDataMap = ConfigQA.getInstance().getServiceDataMap();
        createTrip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();

        putTrip = new Trip.Builder()
                .withId(6L)
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<>() {{
                    add(new Passenger.Builder()
                            .withId(1L)
                            .withRandomCompletely().build());
                }}).build();
    }


    @Test
    public void createTrip() {
        String result = JsonGenerator.toJsonString(createTrip);
        String path = serviceDataMap.get(TripPathEnum.CREATE_TRIP);
        Response response = ApiHelper.post(path, result);
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void getTrip() {
        String path = serviceDataMap.get(TripPathEnum.GET_TRIP);
        Response response = ApiHelper.get(path, 6);
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void putTrip() {
        String path = serviceDataMap.get(TripPathEnum.UPDATE_TRIP);
        String result = JsonGenerator.toJsonString(putTrip);
        Response response = ApiHelper.put(path, result);
        System.out.println(response.getBody().prettyPrint());
    }

    @Test
    public void deleteTrip() {
        String path = serviceDataMap.get(TripPathEnum.DELETE_TRIP);
        Response response = ApiHelper.delete(path, 9);
        System.out.println(response.getBody().prettyPrint());
    }
}


