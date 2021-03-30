//Function code for 7 segment displays, switches and LEDs
// Simplest form of function for these devices.
// In the initial version of CLIC3 the Keypad operations are STUBS CAM 2014-06-02
// Conforms to MISRA C required rules except 109, 110
// MSP430 version
// C.A.Maynard 20140530 v3.00
// Updated 7 segment C.A,Maynard 20140627 v3.01
// ************************************************************************

#include "clic3.h"
// ************************************************************************
ui_16 BusAddress,BusData;
 // The bridge between Assembler code and C code.

// ************************************************************************

// General Interface Initialisation functions
void Initial(void);
void BusRead(void);
void BusWrite(void);
// ************************************************************************

void switchesInit(void) {
}

// Returns true if a new value has been found
// Simplest version assumes there is always a new value.!
enum bool switchesGet(uc_8 *value){
    BusAddress=SwitchesAddr;
    BusRead();
    *value = BusData;
    return true;
}
// ************************************************************************

void LEDsInit(void) {
}

// Returns true if a new value can be stored in the interface
// As there is no way for the system to know just return true.
enum bool LEDsPut(uc_8 value){
    BusAddress=LedsAddr;
    BusData=value;
    BusWrite();
    return true;
}
// ************************************************************************
// In the initial version of CLIC3 the Keypad operations are STUBS CAM 2014-06-02
// Lookup table for operation of the Keypad
#define MaxKeys (16)
const uc_8 LookupKeys[MaxKeys]={ 0x82,0x11,0x12,0x14,0x21,0x22,0x24,0x41,0x42,0x44,0x81,0x84,0x88,0x48,0x28,0x18 };

// Initialise the keypad input interface
void keypadInit(void){
}
// Get a new value from the keypad if there is one otherwise return false
// Returns true if a new value has been found
enum bool keypadGet(uc_8 *value) {
	*value = 3; // Random choice for a stub return
	return true;
}
// ************************************************************************

// Lookup table for operation of the 7segment displays
#define MaxSegs (0x1C)
#define Blank7Seg (0x1B) // Display blank index for the 7 segment display

const uc_8 LookupSeg[MaxSegs]={ 0x040,0x079,0x024,0x030,0x019,0x012,0x002,0x078,0x000,0x018,0x008,0x003,0x046,0x021,0x006,0x00E,
0x027, 0x009, 0x07B, 0x060, 0x047, 0x023, 0x00C, 0x02F, 0x041, 0x011, 0x03F, 0x07F};
/* Beyond index 0x0F (for the hex character "F")
the values represent the characters: c , H , i , J , L , o , P , r , U , Y , - , blank
 With hexadecimal indices           10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1A, 1B
 */

// Initialise the Seven Segment Display output interface
void sevenSegInit(void){
}

/* Send a new value to the selected display and return true otherwise return false if this could not be achieved. 
DispID chooses the display (o or 1). 
value is the selector for what is to be displayed. 
If the result is out of range then blank the display.
*/
enum bool sevenSegPut(uc_8 DispID,uc_8 value) {
    if(DispID) {
    	BusAddress = Seg7AddrL; }
    else {
    	BusAddress = Seg7AddrH; }
    if(MaxSegs > value) {
        BusData = LookupSeg[value];
        BusWrite();
       return true;
    } else {
        BusData = LookupSeg[Blank7Seg];
        BusWrite();
        return false;
  }
}
// ************************************************************************
