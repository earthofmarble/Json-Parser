package jsonparser.service.deprecated;

import jsonparser.model.deprecated.Package;
import jsonparser.model.deprecated.PositionPackage;
@Deprecated
public interface IValueService {

    Package createField(String jsonString);
    PositionPackage getNumberValue(String jsonString);
    String getQuotedValue(String jsonString);

}
