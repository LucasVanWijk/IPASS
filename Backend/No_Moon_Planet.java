package Backend;

public class No_Moon_Planet {
    private double radius;
    private double mass;
    private String name;

    public No_Moon_Planet(double radius, double mass, String name) {
        this.radius = radius;
        this.mass = mass;
        this.name = name;
    }

    public No_Moon_Planet(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return name;
    }

    /**
     * Calculates/receives all the values for the satellite
     * @param satellite is the satellite for which it's parameters need to be calculated
     *
     */
    public void calc_sat_parameters(Satellite satellite) {
        if (satellite.getOrbit_from_surface() == 0) {
            double orbit_distance = Laws.calc_Distance(satellite.getVelocity(), mass);
            satellite.setOrbit_distance(orbit_distance);
        } else {
            //For calculations r is the distance to te center of the planet not it's surface.
            // That is why radius is added to satellite.getOrbit_from_surface()
            satellite.setOrbit_distance(satellite.getOrbit_from_surface() + radius);
        }

        if (satellite.getVelocity() == 0) {
            double velocity = Laws.calc_Velocity(mass, satellite.getOrbit_distance());
            satellite.setVelocity(velocity);
        }
    }

    @Override
    public String toString() {
        return "No_Moon_Planet{" +
                "radius=" + radius +
                ", mass=" + mass +
                ", name='" + name + '\'' +
                '}';
    }
}
