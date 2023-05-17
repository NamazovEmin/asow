package ru.namazov.asow.dictionary;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StationControllerDictionary {
    public static final String STATION_1 =  "{\"id\": 1,\"serialNumber\": 120,\"wagonType\": \"BIG\",\"containerWeight\": 1212,\"carryingCapacity\": 12123}";
    public static final String EXAMPLE_REQUEST_BODY_UPDATE_STATION = STATION_1;
    public static final String EXAMPLE_RESPONSE_UPDATE_STATION_OK_200 = STATION_1;
    public static final String EXAMPLE_REQUEST_BODY_CREATE_STATION = "{\"id\": 1,\"name\": \"Bolshego\"}";
    public static final String EXAMPLE_RESPONSE_GET_STATION_OK_200 = STATION_1;

}
