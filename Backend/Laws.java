package Backend;

public class Laws {
    //Newton's law of universal gravitation states that every particle attracts every other particle
    //in the universe with a force which is directly proportional to the product of their masses
    //and inversely proportional to the square of the distance between their centers.

     // F = G * ((m1*m2) / r^2)

     private static double gravitational_constant =  (6.67408 * Math.pow(10, -11));


    /**
     * Calculates the velocity of the satellite
     * @param mass_planet the mass of the planet
     * @param distance from the satellite to the object (planet)
     * @return the velocity of the satellite
     */
    public static double calc_Velocity(double mass_planet, double distance){
        double velocity = (gravitational_constant * mass_planet) / distance;
        velocity = Math.pow(velocity,0.5);

        return velocity;
    }

    /**
     * Calculates the distance from the satellite to the object(planet)
     * @param velocity of the satellite
     * @param mass_planet the mass of the planet
     * @return the distance from the satellite to the object(planet)
     */
    public static double calc_Distance(double velocity,double mass_planet){
        return (gravitational_constant * mass_planet) / Math.pow(velocity,2);
    }


    /**
     * Calculates the time it takes for one orbit
     * @param distance from the satellite to the object (planet)
     * @param velocity of the satellite
     * @return the time it takes for one orbit
     */
    public static double calc_orbit_time(double distance, double velocity){
        return  (2*Math.PI * distance) / velocity;
    }

}
