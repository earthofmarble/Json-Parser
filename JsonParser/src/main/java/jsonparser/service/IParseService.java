package jsonparser.service;

import jsonparser.model.pack.ObjectWithPosition;

public interface IParseService {

    ObjectWithPosition createJson(String jsonString, int startPosition);

}
