/**
 * HouseCalcTest
 */
public class HouseCalcTest {

    public static void main(String[] args) {
        roomVolumeTest();
    }

    private static void roomVolumeTest()
    {

        assert HouseCalc.roomVolume(4, 3, 5) == 60 : "all values valid error";
        assert HouseCalc.roomVolume(1, 6, 7) == 0 : "width invalid error";
        assert HouseCalc.roomVolume(1, 2, 3) == 0 : "width & Length invalid error";
        assert HouseCalc.roomVolume(1, 2, 2) == 0 : "all values invalid error";
        assert HouseCalc.roomVolume(3, 2, 3) == 0 : "Length invalid error";
        assert HouseCalc.roomVolume(3, 2, 2) == 0 : "Length & Height invalid error";
        assert HouseCalc.roomVolume(1, 3, 2) == 0 : "Width & Height invalid error";
        assert HouseCalc.roomVolume(3, 4, 2) == 0 : "Height invalid error";
        
    }
    
}