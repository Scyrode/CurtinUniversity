; Initialisation of the MSP430 based CLIC3 board
; S. Mokrous 2014 v1.0
;--------------------------------------------------------------------------------------
#include  "msp430f5308.h"

			MODULE      Initialise
			PUBLIC      Initialise
			RSEG         CODE

#define LDOEN               (0x0800u)  /* PU - LDO Enable (3.3V) */
Initialise
           MOV.W    #WDTPW+WDTHOLD,&WDTCTL           ; Stop WDT
           MOV.B    #0A5H,&PMMCTL0_H                 ; Password for Power management controller
/*
Flag1      BIT.W    #SVSMHDLYIFG,&PMMIFG             ; Flag is not set
           JZ       Flag1
Flag2      BIT.W    #SVSMLDLYIFG,&PMMIFG             ; Flag is not set
           JZ       Flag2                            ;
*/
           MOV.W    #(SVSHE+SVSHRVL_1+SVMHE+SVSMHRRL_1),&SVSMHCTL;
           MOV.W    #(SVSLE+SVMLE+SVSMLRRL_1),&SVSMLCTL;
Flag3      BIT.W    #SVSMLDLYIFG,&PMMIFG             ;
           JZ       Flag3                            ;
           BIC.W    #(SVMLVLRIFG+SVMLIFG),&PMMIFG    ;
           MOV.B    #PMMCOREV_1,&PMMCTL0_L           ;
           BIT.W    #SVMLIFG,&PMMIFG                 ;
           JZ       Proceed1                         ;
Flag4      BIT.W    #SVMLVLRIFG,&PMMIFG              ;
           JZ       Flag4                            ;

Proceed1   MOV.W    #(SVSLE+SVSLRVL_1+SVMLE+SVSMLRRL_1),&SVSMLCTL;
;---------------------------------------------------------------------------------------
Flag5      BIT.W    #SVSMHDLYIFG,&PMMIFG             ;
           JZ       Flag5
Flag6      BIT.W    #SVSMLDLYIFG,&PMMIFG             ;
           JZ       Flag6                            ;
           MOV.W    #(SVSHE+SVSHRVL_2+SVMHE+SVSMHRRL_2),&SVSMHCTL;
           MOV.W    #(SVSLE+SVMLE+SVSMLRRL_2),&SVSMLCTL;
Flag7      BIT.W    #SVSMLDLYIFG,&PMMIFG             ;
           JZ        Flag7                           ;
           BIC.W    #(SVMLVLRIFG+SVMLIFG),&PMMIFG    ;
           MOV.B    #PMMCOREV_2,&PMMCTL0_L           ;
           BIT.W    #SVMLIFG,&PMMIFG                 ;
           JZ       Proceed2                         ;
Flag8      BIT.W    #SVMLVLRIFG,&PMMIFG              ;
           JZ       Flag8                            ;

Proceed2   MOV.W    #(SVSLE+SVSLRVL_2+SVMLE+SVSMLRRL_2),&SVSMLCTL;
;---------------------------------------------------------------------------------------
Flag9      BIT.W    #SVSMHDLYIFG,&PMMIFG             ;
           JZ       Flag9
Flag10     BIT.W    #SVSMLDLYIFG,&PMMIFG             ;
           JZ       Flag10                           ;
           MOV.W    #(SVSHE+SVSHRVL_3+SVMHE+SVSMHRRL_3),&SVSMHCTL;
           MOV.W    #(SVSLE+SVMLE+SVSMLRRL_3),&SVSMLCTL;
Flag11     BIT.W    #SVSMLDLYIFG,&PMMIFG             ;
           JZ        Flag11                          ;
           BIC.W    #(SVMLVLRIFG+SVMLIFG),&PMMIFG    ;
           MOV.B    #PMMCOREV_3,&PMMCTL0_L           ;
           BIT.W    #SVMLIFG,&PMMIFG                 ;
           JZ       Proceed3                         ;
Flag12     BIT.W    #SVMLVLRIFG,&PMMIFG              ;
           JZ       Flag12                           ;

Proceed3   MOV.W    #(SVSLE+SVSLRVL_3+SVMLE+SVSMLRRL_3),&SVSMLCTL;
;--------------------------------------------------------------------------------------
           CLR.B    PMMCTL0_H                        ; Switch Off Power management controller
           BIS.W    #DCORSEL_4,&UCSCTL1             ; Set up frequency range
           MOV.W    #763,&UCSCTL2                   ; Devider for FLL: 763 aiming at 25MHz
           MOV.W    #0020H,&UCSCTL3                 ; REFCLK as a reference for FLL
           MOV.W    #0333H,&UCSCTL4                 ; DCOCLK as a souce for ACLK, SMCLK & MCLK
           MOV.W    #65535,R4                       ;
WaitPLL    DEC.W    R4                              ;
           JNZ      WaitPLL                         ;
//           BIS.W    #SCG0,SR                        ; Stop FLL
           MOV.W    #24999,&TA1CCR0                 ; TimerA1 divider set to 1 less than the overflow count
           BIS.W    #(TASSEL_1+MC_1),&TA1CTL        ; AMCLK as a sorce fro Timer1, Timer1 counts to TA1CCR0
           BIS.W    #OUTMOD_4,&TA1CCTL0             ; Toggle mode 500Hz
           BIS.B    #80H,P1DIR                      ; Output direction
           BIS.B    #80H,P1SEL                      ; Select TimerA1
           MOV.W    #0FH,PJDIR                      ; PortJ as an Output
           MOV.W    #00H,PJOUT                      ; CONTROL is 0: passive condition
           MOV.B    #0C0H,P4DIR                     ; E & R/W as an output
           BIC.B    #40H,P4OUT                      ; E passive
           BIS.B    #80H,P4OUT                      ; R/W is High
           BIS.B    #01H,P1REN                    ; Pullup/pulldown enable for P1.0 pin
           BIC.B    #01H,P1OUT                    ; Pulldown resistor for P1.0 pin
           BIS.B    #02H,P1REN                    ; Pullup/pulldown enable for P1.1 pin
           BIC.B    #02H,P1OUT                    ; Pulldown resistor for P1.1 pin
           MOV.W   #9628H,&LDOKEYPID              ; Password for LDO
           BIS.W   #PUOPE,&PUCTL                  ; Enable PU outputs
           BIS.W   #LDOEN,&LDOPWRCTL              ; 3.3V enable
           BIC.W    #PUOUT0,&PUCTL                ; Switch Off buzzer
           BIC.W    #PUOUT1,&PUCTL                ; Reset LCD
           MOV.W    #10000,R4                     ;
ResetLCD1  DEC.W    R4                            ;
           JNZ      ResetLCD1                     ;
           BIS.W    #PUOUT1,&PUCTL                ;
           MOV.W    #10000,R4                     ;
ResetLCD2  DEC.W    R4                            ;
           JNZ      ResetLCD2                     ;
           MOV.W    0(SP),2(SP)                   ;
           INCD.W   SP                            ;
           RET                                    ;

;---------------------------------------------------------------------------------------------------------
           END      Initialise                       ;
