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
    private static Trip createTrip,putTrip,getTrip,deleteTrip;

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
        Trip responseTrip = TripSteps.sendPost(createTrip);
        new TripComparator(createTrip, responseTrip).compare();
    }


    @Test
    public void getTrip() {
        String path = serviceDataMap.get(TripPathEnum.GET_TRIP);
        Response response = ApiHelper.get(path, 6);
        response.getBody().prettyPrint();
    }

    @Test
    public void putTrip() {
        Trip responseTrip = TripSteps.sendPut(putTrip);
        new TripComparator(putTrip, responseTrip).compare();
    }

    @Test
    public void deleteTrip() {
        String path = serviceDataMap.get(TripPathEnum.DELETE_TRIP);
        Response response = ApiHelper.delete(path, 6);
        response.getBody().prettyPrint();
    }

}


