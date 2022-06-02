package models.ships.utils;

import models.ships.utils.exceptions.DirectionException;

public enum Direction {
    HORIZONTAL,
    VERTICAL;

    public static Direction decode(char c) throws DirectionException {
        if (c == 'h' || c == 'H') return HORIZONTAL;
        else if (c == 'v' || c == 'V') return VERTICAL;
        else throw new DirectionException("El carácter '" + c + "' no se puede convertir en una dirección");
    }

    public static Direction decode(String str) throws DirectionException {
        if (str.toLowerCase().equals("horizontal")) return HORIZONTAL;
        else if (str.toLowerCase().equals("vertical")) return VERTICAL;
        else throw new DirectionException("La cadena '" + str + "' no se puede convertir en una dirección");
    }
}
