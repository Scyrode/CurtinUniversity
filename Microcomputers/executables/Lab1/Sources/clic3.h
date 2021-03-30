/* Header file to provide basic definitions for the MSP430
   embedded systems in the IAR programming environment at Curtin Uni
   CAM 20140530 v3.00
   
   Include prototypes for Initialise, switch and LED operations
   Conforms to MISRA C required rules EXCEPT 109, 110
   NOTE: IAR supplied files do not need to conform to MISRA C rules.
   
   Minor changes CAM 20140611 v3.01
	Keypad functionality updated CAM 20140630 v3.02
*/
// ************************************************************************
#ifndef clic3defs
    #define clic3defs

#include <io430f5308.h>
#include  <intrinsics.h> //v3.02

// Interface Global addresses set here
#define SwitchesAddr (0x4000)
#define LedsAddr (0x4002)
#define Seg7AddrL (0x4004)
#define Seg7AddrH (0x4006)
#define KeyPadAddr (0x4008)

// General Interface Initialisation functions
void Initial(void);
void BusRead(void);
void BusWrite(void);

// Typedefs for MISRA C compliance .... CAMaynard 20091110
    /* MISRA Rule 13 */
    typedef unsigned char uc_8;
    typedef signed char sc_8;
    typedef unsigned int ui_16;
    typedef signed int si_16;
    typedef long int si_32;
    typedef unsigned long int ui_32;

    /* Ensure that flags/Boolean variables can only be true or false */
    enum bool {false,true};
// ***************************************************************************
    /* Structure & Union to allow access to individual bytes in an integer for the MSP430
    * Configured and named for general signed and unsigned application.
    * Reconfigured as a SMALL ENDIAN solution for the MSP430
    *
    * Use: declare variable "fred" of type:                union split fred
    * then access to the unsigned low byte of fred is given by      fred.ubytes.ulow
    * access to the unsigned int within fred is given by   fred.usmallend          etc
    *
    * NOTE: Not normal under MISRA guidelines Rules 109 and 110, however justified because the alignment
    * and packing of the data types IS determinate, consistent and understood for the MSP430 */

    struct uhighlow {
    volatile uc_8 ulow; /* Most significant unsigned byte  */
    volatile uc_8 uhigh;  /* Least significant unsigned byte */
    };
    struct shighlow {
    volatile sc_8 slow; /* Most significant signed byte  */
    volatile sc_8 shigh;  /* Least significant signed byte */
    };
    union split {
    volatile ui_16 usmallend;      /* 16 bit unsigned integer */
    volatile si_16 ssmallend;      /* 16bit signed integer    */
    struct   uhighlow ubytes ;   /* Small endian bytes        */
    struct   shighlow sbytes ;
    };
// ****************************************************************************

//Prototype for system Initialisation
void Initialise(void);

// Prototypes for the Switches , LED, keypad and sevensegment display functions
// ****************************************************************************
// Initialise the switch input interface
void switchesInit(void);
// Get a new value from the switches if there is one otherwise return false
enum bool switchesGet(uc_8 *value); // Returns true if a new value has been found
// ****************************************************************************

#define BlankLEDs (0x0FF) // Value to blank out the LEDs
// Initialise the LEDs output interface and blanks the display
void LEDsInit(void);
// Send a new value to the display and return true otherwise return false if this could not be achieved.
enum bool LEDsPut(uc_8 value);
// ****************************************************************************

// Initialise the keypad input interface
// In the initial version of CLIC3 the Keypad operations are STUBS CAM 2014-06-02
// Interrupt used so KEYPAD INTERRUPT MUST BE ON.
// THIS TURNS ON INTERRUPTS
void keypadInit(void);
// Get a new value from the switches if there is one otherwise return false
enum bool keypadGet(uc_8 *value); // Returns true if a new value has been found
// ****************************************************************************

#define MaxSegs (0x1C) // Number of displayable characters in the array.
#define Blank7Seg (0x1B) // Display blank index for the 7 segment display
/* For values 0x00 thru 0x0F the hexadecimal digit will be displayed
Beyond 0c0F values represent selector for the characters: c , H , i , J , L , o , P , r , U , Y , - , blank
With hexadecimal      selectors of                       10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 1A, 1B
 */
// Seven SegmentDevice IDs
#define DispID1 (1)
#define DispID2 (0)

// Initialise the Seven Segment Display output interface including blanking the displays
void sevenSegInit(void);
// Send a new value to the selected display and return true otherwise return false if this could not be achieved. DispID chooses the display (o or 1). value is the selector for what is to be displayed.
enum bool sevenSegPut(uc_8 DispID,uc_8 value);

#endif //end of clic3defs
