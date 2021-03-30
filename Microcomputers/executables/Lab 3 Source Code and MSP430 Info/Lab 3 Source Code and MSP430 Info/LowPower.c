// Introduction to the MSP430FR5739 system
// As provided this program will pulse a blue LED on the board
// Removing the necessary line comment will set up two LEDs to toggle between them
// using a software delay to make the display visible
//
// A better setup is to use a timer and interrupts
// Comment out the __delay_cycles function
// Restore all the other lines to facilitate the timer and interrupt operations
// This uses a low power mode to wait for an interrupt to occur.
// CAM 20130213
// ******************************************************************
#include "msp430fr5739.h"

unsigned int counter = 0;

void main(void)
{
  WDTCTL = WDTPW + WDTHOLD;                 // Stop WDT  
 
 // Set up a timer and enable the interrupt
  TA1CCR0 = 0x4000;
  TA1CTL = TASSEL_1 + MC_1 + TACLR;
  TA1CCTL0 = CCIE;                          // interrupt enabled


// Set up one LED to pulse
  P3DIR |= BIT7;
  P3OUT |= BIT7;
// Remove comments below to........Toggle between two LEDs  
  P3DIR |= BIT5;
  P3OUT ^= BIT5;
  PJDIR |= BIT3;
  PJOUT ^= BIT3;
  PJDIR |= BIT1;
  PJOUT ^= BIT1;
  P3DIR |= BIT6;
  P3OUT &= ~BIT6;
  

// turning external LED on
  P3DIR |= BIT0;
  P3OUT |= BIT0;
  
  while(1)
  {
//      P3OUT ^= BIT0; // turns on external LED
      __low_power_mode_3();
      P3OUT ^= (BIT7 + BIT6 + BIT5 + BIT4);
      PJOUT ^= (BIT3 + BIT2 + BIT1 + BIT0);
      __delay_cycles(100000);
      counter++;

  }
}

// The interrupt service routine
 #pragma vector = TIMER1_A0_VECTOR
__interrupt void Timer1_A0_ISR(void)
{
	__low_power_mode_off_on_exit();
}

