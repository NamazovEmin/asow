package ru.namazov.asow.dictionary;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WagonControllerDictionary {
    public static final String WAGON_PASSPORT_1 =  "{\"id\": 1,\"serialNumber\": 120,\"wagonType\": \"BIG\",\"containerWeight\": 1212,\"carryingCapacity\": 12123}";
    public static final String EXAMPLE_REQUEST_BODY_UPDATE_WAGON_PASSPORT = WAGON_PASSPORT_1;
    public static final String EXAMPLE_RESPONSE_UPDATE_WAGON_PASSPORT_OK_200 = WAGON_PASSPORT_1;
    public static final String EXAMPLE_REQUEST_BODY_CREATE_WAGON_PASSPORT = "{\"id\": 1,\"serialNumber\": 1220,\"wagonType\": \"BIG\",\"containerWeight\": 1212,\"carryingCapacity\": 12123}";
    public static final String EXAMPLE_RESPONSE_GET_WAGON_PASSPORT_OK_200 = WAGON_PASSPORT_1;

}
