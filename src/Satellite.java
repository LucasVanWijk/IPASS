public class Satellite {
    private double mass;
    private double velocity;
    private double orbit_distance;

    public Satellite(double mass, double velocity, double orbit_distance) {
        this.mass = mass;
        this.velocity = velocity;
        this.orbit_distance = orbit_distance;
    }

    public Satellite() {
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
