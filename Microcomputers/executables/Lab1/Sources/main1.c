/* First program for the CLIC3 board. CAM20140530
Calls the initialisation and operational functions for the devices being used.
Input the switch settings.
Invert the switch bits and display on the LEDs
Output displayable switch values to the Seven Segment displays.
NOTE the boolean flags are not used in the first laboratory.
Uses:
clic3.h v3.02, clic3.c v3.01
initialise.asm v1.0, BusRead v1.3, BusWrite v2.3
******************************************************************** */

#include "clic3.h"

const int Seg1 = 0;
const int Seg2 = 1;

uc_8 temp; // Global for debug testing only

void main(void) {

 enum bool flag1, flag2, flag3, flag4;
  
Initialise(); // System initialisation
switchesInit(); // Switches initialisation
LEDsInit(); // LED initialisation
sevenSegInit(); // 7segment initialisation

	for(;;) {
    flag1 = switchesGet(&temp);
    flag2 = LEDsPut(~temp);
    flag3 = sevenSegPut(Seg1, temp);
    flag4 = sevenSegPut(Seg2, temp);

	} /* loop forever */
}
