package jsonparser.service.impl;

import jsonparser.service.ISymbolService;

public class SymbolService implements ISymbolService {

    /**
     * @return returns a character next to given startPosition, ignores all spaces, new line symbols and commas.
     * Throws an exception if nothing found.
     */
    public Character getNextChar(String string, int startPosition) {
        for (int charIndex = startPosition + 1; charIndex < string.length(); charIndex++) {
            char currentChar = string.charAt(charIndex);
            if (!(currentChar == ' ' || currentChar == '\n' || currentChar == ',')) {
                return currentChar;
            }
        }
        throw new RuntimeException("There is no chars after position [" + startPosition + "] in: [" + string + "] except commas, spaces and new lines");
    }

    /**
     * @return returns true if given character is a digit.
     */
    public boolean isNumber(Character character) {
        final String NUMBERS = "1234567890.";
        return NUMBERS.contains(character.toString());
    }

    /**
     * @return returns true if given char equals 'n'
     */
    public boolean isNull(Character character) {
        return character == 'n';
    }

    /**
     * @return returns true if given char equals 't' or 'f'
     */
    public boolean isBoolean(Character character) {
        return character == 't' || character == 'f';
    }

}
