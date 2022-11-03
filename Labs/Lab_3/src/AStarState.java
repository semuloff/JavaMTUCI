import java.util.*;


/**
 * This class stores the basic state necessary for the A* algorithm to compute
 * a path across a map.  This state includes a collection of "open waypoints"
 * and another collection of "closed waypoints."  In addition, this class
 * provides the basic operations that the A* pathfinding algorithm needs to
 * perform its processing.
 **/
public class AStarState
{
    /**
     * This is a reference to the map that the A* algorithm
     * is navigating.
     **/
    private Map2D map;

    /** Определяем и инициализируем поле для открытых вершин **/
    private Map<Location, Waypoint> openWaypoints
            = new HashMap<Location, Waypoint> ();

    /** Определяем и инициализируем поле для закрытых вершин **/
    private Map<Location, Waypoint> closedWaypoints
            = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this
     * method returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // Если в открытом наборе нет вершин - возвращаем null.
        if (numOpenWaypoints() == 0)
            return null;
        
        // Инициализируем набор ключей всех открытых путевых точек.
        Set openWaypointKeys = openWaypoints.keySet();

        // Инициалицируем итератор для перебора ключей
        Iterator _iterator = openWaypointKeys.iterator();

        // Инициализируем лучшую путевую точку.
        Waypoint bestWaypoint = null;

        // Инициализируем стоимость лучшей путевой точки.
        float bestValue = Float.MAX_VALUE;

        // Сканирует все открытые путевые точки.
        while (_iterator.hasNext()) {

            // Сохраняет текущее местоположение.
            Location location = (Location)_iterator.next();

            // Сохраняет текущую путевую точку.
            Waypoint waypoint = openWaypoints.get(location);

            // Сохраняет общую стоимость для текущей путевой точки.
            float waypointTotalValue = waypoint.getTotalCost();

            /** / Если общая стоимость для текущей путевой точки лучше (ниже),
             * чем сохраненная стоимость для сохраненной лучшей путевой точки,
             * заменяем сохраненную путевую точку на текущую путевую точку,
             * а сохраненную общую стоимость на текущую общую стоимость.**/
            if (waypointTotalValue < bestValue)
            {
                bestWaypoint = openWaypoints.get(location);
                bestValue = waypointTotalValue;
            }

        }
        // Возвращаем лучшую путевую точку.
        return bestWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint
     * already in) the "open waypoints" collection.  If there is not already
     * an open waypoint at the new waypoint's location then the new waypoint
     * is simply added to the collection.  However, if there is already a
     * waypoint at the new waypoint's location, the new waypoint replaces
     * the old one <em>only if</em> the new waypoint's "previous cost" value
     * is less than the current waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Находит местоположение новой путевой точки.
        Location location = newWP.getLocation();

        // Проверяет, есть ли уже открытая путевая точка в местоположении новой путевой точки.
        if (openWaypoints.containsKey(location)) {
            Waypoint currentWaypoint = openWaypoints.get(location);
            if (newWP.getPreviousCost() < currentWaypoint.getPreviousCost())
            {
                /**
                 * Если значение «предыдущая стоимость» новой путевой точки меньше,
                 * чем значение «предыдущая стоимость» текущей путевой точки,
                 * новая путевая точка заменяет старую путевую точку и возвращает значение true.
                 * **/
                openWaypoints.put(location, newWP);
                return true;
            }
            else
                return false;
        }

        /**
         * Если в местоположении новой путевой точки еще нет открытой путевой точки,
         * добавим новую путевую точку в коллекцию открытых путевых точек и верните true.
         * **/
        openWaypoints.put(location, newWP);
        return true;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     * Этот метод перемещает путевую точку в указанное место из
     * набора открытых вершин в закрытый набор.
     **/
    public void closeWaypoint(Location location)
    {
        Waypoint wayPoint = openWaypoints.remove(location);
        closedWaypoints.put(location, wayPoint);
    }

    /**
     * Возвращает true, если набор закрытых путевых точек содержит путевую точку
     * для указанного места.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closedWaypoints.containsKey(loc);
    }
}