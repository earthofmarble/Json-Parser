package jsonparser.service.upd.impl;

import jsonparser.enumeration.ValueType;
import jsonparser.service.upd.ISymbolService;

public class SymbolService implements ISymbolService {

    /**
     * @param innerValue string value to work with
     * @param position starting position
     * @return returns ValueType based on first symbol of string
     */
    public ValueType getInnerType(String innerValue, int position) {
        while (true) {
            Character nextSymbol = getNextChar(innerValue, position);
            if (nextSymbol == null) {
                return ValueType.NONE;
            }
            if (isNumber(nextSymbol)) {
                return ValueType.NUMBER;
            }
            switch (nextSymbol) {
                case '{':
                    return ValueType.OBJECT;
                case '[':
                    return ValueType.ARRAY;
                case '"':
                    return ValueType.STRING;
                case 'n':
                    return ValueType.NULL;
                case 't':
                case 'f':
                    return ValueType.BOOLEAN;
                case ':':
                case ',':
                    position++;
                    continue;
            }
            return ValueType.NONE;
        }
    }

    /**
     * @param string string to work with
     * @param position starting position
     * @return returns next char of a string ignoring spaces
     */
    public Character getNextChar(String string, int position) {
        for (int charIndex = position + 1; charIndex < string.length(); charIndex++) {  // to remove +1
            char currentChar = string.charAt(charIndex);
            if (!(currentChar == ' ' || currentChar == '\n' || currentChar == ',')) {
                return currentChar;
            }
        }
        return null;
    }

    /**
     * @param character any symbol
     * @return returns true if symbol is a number
     */
    public boolean isNumber(Character character) {
        final String NUMBERS = "1234567890.";
        return NUMBERS.contains(character.toString());
    }

    public boolean isNull(Character character){
        return character == 'n';
    }

    public boolean isBoolean(Character character){
        return character == 't' || character=='f';
    }

}
