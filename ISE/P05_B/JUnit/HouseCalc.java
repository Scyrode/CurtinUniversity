// Production code for ISE worksheet 5B.

public class HouseCalc
{
    /**
     * Calculates the volume of a room, but only if the imported width, length 
     * and height are valid. To be valid, width must be at least 2 (metres), 
     * length 2.5, and height 3. For invalid imports, this submodule will 
     * return 0.
     */
    public static double roomVolume(double width, double length, double height)
    {
        double volume = 0;
        if(width >= 2.0 || length >= 2.5 || height >= 3.0)
        {
            volume = width * length * height;
        }
        return volume;
    }
}