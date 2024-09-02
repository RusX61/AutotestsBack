package model;

import dictionaries.IPathEnum;
import dictionaries.TripPathEnum;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import java.util.Collections;
import java.util.Map;
import static model.JsonGenerator.toJsonString;

public class TripSteps {

    private static final Map<IPathEnum, String> serviceDataMap = ConfigQA.getInstance().getServiceDataMap();

    public static Trip sendPost(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.CREATE_TRIP);
        Response response = ApiHelper.post(path, toJsonString(trip));
        return doCommonOperation(response);
    }

    public static Trip sendGet(long id) {
        String path = serviceDataMap.get(TripPathEnum.GET_TRIP);
        Response response = ApiHelper.get(path, id);
        return doCommonOperation(response);
    }

    public static Trip sendPut(Trip trip) {
        String path = serviceDataMap.get(TripPathEnum.UPDATE_TRIP);
        Response response = ApiHelper.put(path, toJsonString(trip));
        return doCommonOperation(response);
    }

    public static Trip sendDelete(long id) {
        String path = serviceDataMap.get(TripPathEnum.DELETE_TRIP);
        Response response = ApiHelper.delete(path, id);
        return doCommonOperation(response);
    }

    private static Trip doCommonOperation(Response response) {
        Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
        Trip responseTrip = response.as(Trip.class);
        Collections.sort(responseTrip.getPassengerList());
        return responseTrip;
    }
}
