/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    // переопределение метода equals()
    public boolean equals(Object object) {

        // проверка объекта на принадлежность к классу Location?
        if (object instanceof Location) {
            Location otherCoordinate = (Location) object;

            // проверяем координаты объекта на равенство текущих координат.
            if (xCoord == otherCoordinate.xCoord && yCoord == otherCoordinate.yCoord)
                return true;
        }

        return false;
    }

    public int hashCode() {
        // определяем и инициализируем любое число.
        int answer = 11;

        // создаем для объекта свой хэшкод.
        answer = xCoord + 111 * answer;
        answer = yCoord + 29 * answer;

        return answer;
    }
}