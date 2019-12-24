package jsonparser.service;

import jsonparser.model.pack.ObjectWithPosition;

public interface IValueService {

    ObjectWithPosition createPair(String jsonString, int startPosition);

    ObjectWithPosition createStringValue(String jsonString, int startPosition);

    ObjectWithPosition createNumericValue(String jsonString, int startPosition);

    ObjectWithPosition createBooleanValue(String jsonString, int startPosition);

}
