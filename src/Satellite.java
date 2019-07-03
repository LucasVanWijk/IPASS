public class Satellite {
    private double mass;
    private double velocity;
    private double orbit_distance;
    private double orbit_from_surface;
    private String name;

    public Satellite(double mass, double velocity, double orbit_from_surface, String name) {
        this.mass = mass;
        this.velocity = velocity;
        this.orbit_from_surface = orbit_from_surface;
        this.name = name;
    }



    public double getOrbit_from_surface() {
        return orbit_from_surface;
    }

    public void setOrbit_from_surface(double orbit_from_surface) {
        this.orbit_from_surface = orbit_from_surface;
    }

    public String getName() {
        return name;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getOrbit_distance() {
        return orbit_distance;
    }

    public void setOrbit_distance(double orbit_distance) {
        this.orbit_distance = orbit_distance;
    }

}
