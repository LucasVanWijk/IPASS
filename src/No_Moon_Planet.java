public class No_Moon_Planet {
    private double diameter;
    private double mass;
    private String name;

    public No_Moon_Planet(double diameter, double mass,String name) {
        this.diameter = diameter;
        this.mass = mass;
        this.name = name;
    }

    public No_Moon_Planet(String name) {
        this.name = name;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
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

    public void setName(String name) {
        this.name = name;
    }

    public void orbit_sim(Satellite satellite) {
        calc_sat_parameters(satellite);
        System.out.println("\n");
        orbit_return(satellite);

    }

    private void calc_sat_parameters(Satellite satellite) {
        if (satellite.getOrbit_from_surface() == 0) {
            double orbit_distance = Laws.calc_Distance(satellite.getVelocity(), mass);
            satellite.setOrbit_distance(orbit_distance);
        } else {
            //For calculations r is the distance to te center of the planet not it's surface.
            // This makes sure the right values are used
            satellite.setOrbit_distance(satellite.getOrbit_from_surface() + diameter);
        }

        if (satellite.getVelocity() == 0) {
            double velocity = Laws.calc_Velocity(mass, satellite.getOrbit_distance());
            satellite.setVelocity(velocity);
        }

        if (satellite.getMass() == 0) {
            System.out.println("Please give a mass to the statellite. Calculations can not be made without it");
        }
    }

    private void orbit_return(Satellite satellite) {
        System.out.println("The mass of the sat is " + satellite.getMass());
        System.out.println("The velocity of the sat is " + satellite.getVelocity());
        System.out.println("It orbits at a distance form the planet of " + (satellite.getOrbit_distance() - diameter));
        System.out.println("from the center " + satellite.getOrbit_distance());
        System.out.println("It takes " + Laws.calc_orbit_time(satellite.getOrbit_distance(), satellite.getVelocity()) +
                " seconds to compleet a orbit" + " or " +
                Laws.calc_orbit_time(satellite.getOrbit_distance(), satellite.getVelocity()) / 60 / 60 + " Hours");
        System.out.println("Energy " + Laws.calc_specific_orbital_energy(satellite.getVelocity(), mass,
                satellite.getMass(), satellite.getOrbit_distance()));

    }

}
