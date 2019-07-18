package Backend;

public class Laws {
    //Newton's law of universal gravitation states that every particle attracts every other particle
    //in the universe with a force which is directly proportional to the product of their masses
    //and inversely proportional to the square of the distance between their centers.

     // F = G * ((m1*m2) / r^2)

    //Based on the 2014 CODATA-recommended value of the gravitational constant
     private static double gravitational_constant =  (6.67408 * Math.pow(10, -11));


    public static double calc_Velocity(double mass_planet, double distance){
        double velocity = (gravitational_constant * mass_planet) / distance;
        velocity = Math.pow(velocity,0.5);

        return velocity;
    }
    public static double calc_Distance(double velocity,double mass_planet){
        return (gravitational_constant * mass_planet) / Math.pow(velocity,2);
    }


    public static double calc_orbit_time(double distance, double velocity){
        return  (2*Math.PI * distance) / velocity;
    }

    public static double calc_specific_orbital_energy(double velocity,double mass_planet, double mass_sat,double distance){
        double u = (mass_planet + mass_sat) * gravitational_constant;
        return  (Math.pow(velocity,2) / 2) - u/distance;
    }
}
