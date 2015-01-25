import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.MoveTo;

/**
* This class creates an enumeration of the first four planets with stored data.
* @author Austin Herring
* @version 1.0
*/
public enum Planet {

    // I found the days for each of the planet periods on
    //http://www.telescope.org/nuffield/pas/solar/solar7.html
    //then converted them to seconds for the sake of javaFX

    // I found the radii as well as the shortest and longest distance of
    // each planet's orbits on
    // http://hyperphysics.phy-astr.gsu.edu/hbase/solar/soldata2.html#c1
    // and used it to calculate the elliptical orbits
    // The planets are scaled to Neptune's radius, the largest.

    MERCURY(Color.SILVER, 336.6 * .0123, 330 * .0154, .382, 7603200),
    VENUS(Color.BROWN, 336.6 * .0239, 330 * .0241, .949, 19414080),
    EARTH(Color.SPRINGGREEN, 336.6 * .0335, 330 * .033, 1, 31553280),
    MARS(Color.RED, 336.6 * .0548, 330 * .0464, .532, 59356800),
    JUPITER(Color.ORANGE, 336.6 * .1794, 330 * .1663, 11.19, 374284800),
    SATURN(Color.LIGHTYELLOW, 336.6 * .3306, 330 * .3025, 9.26, 929664000),
    URANUS(Color.BLUE, 336.6 * .6605, 330 * .6147, 4.01, 2652480000L),
    NEPTUNE(Color.LIGHTBLUE, 336.6, 330, 3.88, 5201280000L);

    private final Color color;
    private final double period;
    private final double radius;
    private final double distanceX;
    private final double distanceY;

    Planet(Color color, double distanceX, double distanceY, double radius, double period) {
        this.color = color;
        this.period = period;
        this.radius = radius;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }

    /**
    * This method create a planet with according radius and color.
    * @return Circle of a planet.
    */
    public Circle circle(double radius) {
        return new Circle(radius, color);
    }

    /**
    * This method creates and orbit with appropriate distance from sun.
    * @return Path of a planet.
    */
    public Path path(double distanceX, double distanceY) {
        Path path = new Path();
        ArcTo arcTo1 = new ArcTo();
        ArcTo arcTo2 = new ArcTo();

        // This condtional accounts only for Mercury's eccentricity
        if (distanceX == 336.6 * .0123 * 18) {
            path.getElements().add(new MoveTo(608.892, 341.231 - distanceY));
            arcTo1.setX(608.892);
            arcTo1.setY(341.231 + distanceY);
            arcTo2.setX(608.892);
            arcTo2.setY(341.231 - distanceY);
            arcTo1.setXAxisRotation(50.2317);
            arcTo2.setXAxisRotation(50.2317);
        } else {
            path.getElements().add(new MoveTo(600, 350 - distanceY));
            arcTo1.setX(600);
            arcTo1.setY(350 + distanceY);
            arcTo2.setX(600);
            arcTo2.setY(350 - distanceY);
        }

        arcTo1.setRadiusX(distanceX);
        arcTo1.setRadiusY(distanceY);
        arcTo2.setRadiusX(-distanceX);
        arcTo2.setRadiusY(-distanceY);

        path.getElements().add(arcTo1);
        path.getElements().add(arcTo2);
        path.getElements().add(new ClosePath());
        path.setStroke(Color.WHITE);

        return path;
    }

    /**
    * This method finds the time it takes for a planet to orbit te sun once.
    * @return double period of planet.
    */
    public double period() {
        return period;
    }

    /**
     * This method the radius of a planet.
     * @return double radius of planet.
     */
    public double radius() { return radius; }

    /**
     * This method finds the x component of the planet's elliptical orbit.
     * @return double distanceX of planet.
     */
    public double distanceX() { return distanceX; }

    /**
     * This method finds the y component of the planet's elliptical orbit.
     * @return double distanceY of planet.
     */
    public double distanceY() { return distanceY; }
}
