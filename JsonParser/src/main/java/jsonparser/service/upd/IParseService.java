package jsonparser.service.upd;

import jsonparser.model.pack.ObjectWithPosition;

public interface IParseService {

    ObjectWithPosition createJson(String jsonString, int startPosition);

}
