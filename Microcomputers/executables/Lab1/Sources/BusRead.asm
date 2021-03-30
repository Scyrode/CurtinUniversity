#include  "msp430f5308.h"

; Reading 2 bytes from a specified memory address
; Pseudo Memory Mapped Operations for the CLIC peripherals 
; attached to the MSP430 microcontroller.
; Designed to attach the current controller to LEGACY hardware (designed for the S12)
; S. Mokroous 2014-06-09 v2.0
; Modifications and Additional documentation C.A.Maynard
; Included Extern functionality for linking to other code.
;C.A.Maynard 20140704 v1.2
; Minor changes SM 20140714 v1.3 file BusRead2.asm
; *************************************************************************


            MODULE      BusRead
            PUBLIC      BusRead
            
EXTERN BusAddress, BusData

            RSEG       DATA16_C
ADDRCONST     DC16    BusAddress;        ; The bridge between Assembler code and C code
DATACONST     DC16    BusData;           ; The bridge between Assembler code and C code                         

;------------------------------------------------------------------------------------------------------


            RSEG        CODE                        ;


BusRead 

           BIC.B    #03H,P1IE                       ;  Disable P1.0 P1.1 interrupts CAM

; Save registers used           

           PUSH.W   R4                              ;          
           PUSH.W   R5                              ;          
           PUSH.W   R6                              ;           
           PUSH.W   R7                              ;
           MOV.B    #0FH,P5DIR                      ; PORT5 as an Output


;Get the bus address value
           MOV.W    ADDRCONST,R5                    ;          
           MOV.W    @R5,R4                          ; Get the memory address from 1C00H           
           MOV.W    R4,R5                           ; Copy into R5


           MOV.W    #01,PJOUT                       ; Control1: Open gate for the least significant nibble          
           MOV.B    R4,P5OUT                        ; Put least significant nibble out of Port5     


           MOV.W    #02,PJOUT                       ; Control2: Lock the latch of the least significant nibble           

           RRA.B    R4                              ; Get the next nibble ready           
           RRA.B    R4                              ;           
           RRA.B    R4                              ;           
           RRA.B    R4                              ;

           MOV.B    R4,P5OUT                        ;
                                                                                       

           MOV.W    #03H,PJOUT                      ; Control3: Lock the latch of the next nibble    
                      
           SWPB     R5                              ; Get the other byte                       
           MOV.B    R5,P5OUT                        ; Output nibble
           MOV.W    #04,PJOUT                       ; Control4: Lock the latch of the nibble     
                                                                   

           RRA.B    R5                              ; Get the last nibble in position           
           RRA.B    R5                              ;           
           RRA.B    R5                              ;           
           RRA.B    R5                              ;           
           MOV.B    R5,P5OUT                        ;           
           MOV.W    #05H,PJOUT                      ; Control5: Lock the last nibble and get Address on the bus          
           NOP                                      ;  Give it a chance to appear           
           NOP                                      ;           
           NOP                                      ;
           
           
           MOV.B    #00H,P5DIR                      ; PORT5 as an Input           

           BIS.B    #40H,P4OUT                      ; E active from S12 system
           
           NOP                                      ; Match timing requirements with external peripheral           
           NOP                                      ;                      
           NOP                                      ;           
           NOP                                      ;
           NOP                                      ;           
           NOP                                      ;           
           NOP                                      ;
           NOP                                      ;
           NOP                                      ;           
           NOP                                      ;
           
           
//           NOP                                      ;           
//           NOP                                      ;
           
           
           

           MOV.W    #11,PJOUT                       ; Control11 to collect first nibble           
           MOV.B    P5IN,R4                         ; Get the first nibble
           AND.W    #0FH,R4                         ;Mask it off       
         
           
           MOV.W    #12,PJOUT                       ; Control12 to collect second nibble                                                   
           MOV.B    P5IN,R5                         ; Get the secont nibble
           AND.W    #0FH,R5                         ;Mask it off    


           MOV.W    #13,PJOUT                       ; Control13 to clollect third nibble           
           MOV.B    P5IN,R6                         ; Get the third nibble
           AND.W    #0FH,R6                         ;Mask it off

                     
           MOV.W    #14,PJOUT                       ; Control14 to collect fourth nibble                                
           MOV.B    P5IN,R7                         ; Get the fourth nibble
           AND.W    #0FH,R7                         ;Mask it off

 
 
           RLA.B    R5                              ; Move nibble in R5 left four places
           RLA.B    R5                              ;           
           RLA.B    R5                              ;
           RLA.B    R5                              ;
           RLA.B    R7                              ; Move nibble in R7 left four spaces
           RLA.B    R7                              ;           
           RLA.B    R7                              ;
           RLA.B    R7                              ;                                            
           ADD.W    R4,R5                           ; Set least significant byte in R5
           ADD.W    R6,R7                           ; Set most significant byte in lower byte of R7
           SWPB     R7                              ; Move the bytes over in R7
           ADD.W    R5,R7                           ; Put it all together in R7 for return          
                   

           MOV.W    DATACONST,R5                    ;           
           MOV.W    R7,0(R5)                        ; Data ready  

           
           BIC.B    #40H,P4OUT                      ; E passive           
           MOV.W    #00,PJOUT                       ; Disconnect all



           POP.W    R7                              ; Clean up the stack
           POP.W    R6                              ;          
           POP.W    R5                              ;           
           POP.W    R4                              ;


                                            
           MOV.W    0(SP),2(SP)                     ;
           
           INCD.W   SP                              ;
                      
      
           BIS.B    #03H,P1IE                       ;  Enable P1.0 P1.1 interrupt CAM           
           RET                                      ;


;--------------------------------------------------------------------------------------------------

           END         BusRead                      ;




