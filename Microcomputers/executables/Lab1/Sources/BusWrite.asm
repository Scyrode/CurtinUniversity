
#include  "msp430f5308.h"

; Writing 2 bytes from a specified memory address
; Pseudo Memory Mapped Operations for the CLIC peripherals 
; attached to the MSP430 microcontroller.
; Designed to attach the current controller to LEGACY hardware (designed for the S12)
; S. Mokroous 2014-06-10 v2.0
; Modifications and Additional documentation C.A.Maynard
; Included Extern functionality for linking to other code.
;C.A.Maynard v2.1

; Minor changes SM 20140714 v2.3 file BusWrite2.asm
; *************************************************************************



            MODULE      BusWrite
            PUBLIC      BusWrite 
            
EXTERN BusAddress, BusData


            RSEG       DATA16_C
ADDRCONST     DC16    BusAddress;        ; The bridge between Assembler code and C code
DATACONST     DC16    BusData;           ; The bridge between Assembler code and C code                         

;----------------------------------------------------------------------------------------------------------



            
            RSEG        CODE
            
            
BusWrite 



           BIC.B    #03H,P1IE                       ;  Disable P1.0 and P1.1 interrupts CAM
           PUSH.W   R4                              ;           
           PUSH.W   R5                              ;
           MOV.B    #0FH,P5DIR                      ; PORT as an Output
           

;Get the bus address value
           MOV.W    ADDRCONST,R5                    ;
           MOV.W    @R5,R4                          ; Get the memory address from 1C00H
           MOV.W    R4,R5                           ; Copy into R5 



           MOV.W    #01,PJOUT                       ; Control1: Open the gate to the least significant nibble  
           MOV.B    R4,P5OUT                        ;
           MOV.W    #02,PJOUT                       ; Control2: Lock least significant nibble and open gate for the second one    
            
          
           RRA.B    R4                              ; Get this nibble ready           
           RRA.B    R4                              ;           
           RRA.B    R4                              ;           
           RRA.B    R4                              ;

           MOV.B    R4,P5OUT                        ; Output this nibble           
           MOV.W   #03H,PJOUT                       ; Control3: Lock the second nibble and open gate for the third nibble  
 
 
           SWPB     R5                              ; Prepare the second byte                                                                                             
           MOV.B    R5,P5OUT                        ;                      
           MOV.W    #04,PJOUT                       ; Control4: Lock the third nibble and apen gate for the fourth nibble 

          
           RRA.B    R5                              ; Get the last nibble in position           
           RRA.B    R5                              ;           
           RRA.B    R5                              ;           
           RRA.B    R5                              ;
           MOV.B    R5,P5OUT                        ;           
           MOV.W    #06,PJOUT                       ; Control6: Lock the fourth nibble and open gate for the first data nibble 



           MOV.W    DATACONST,R5                    ;            
           MOV.W    @R5,R4                          ; From data from address 1C02H
           MOV.W    R4,R5                           ; Copy into R5
                     
           MOV.B    R4,P5OUT                        ; First data nibble                                                        
           MOV.W    #07,PJOUT                       ; Control7: Lock the first data nibble and open the gate for the second data nibble  


           RRA.B    R4                              ; Get the second data nibble in position                                 
           RRA.B    R4                              ;           
           RRA.B    R4                              ;                                
           RRA.B    R4                              ;                                         
           MOV.B    R4,P5OUT                        ;           
           MOV.W    #08,PJOUT                       ; Control8: Lock the second data nibble and open gate for the third data nibble  

                                                                                         
           SWPB     R5                              ; Get high order byte
           MOV.B    R5,P5OUT                        ; Next nibble out          
           MOV.W    #09,PJOUT                       ; Control9: Lock the third data nibble and open the gate for the fourth data nibble 


           RRA.B    R5                              ;                                
           RRA.B    R5                              ;          
           RRA.B    R5                              ;                                 
           RRA.B    R5                              ;
           MOV.B    R5,P5OUT                        ; Most significant data nibble out                                                       
           
           MOV.W    #05H,PJOUT                      ; Control5: Lock most significant nibble and put address on the bus
           NOP                                      ;            
           NOP                                      ;           
           NOP                                      ;                                                
           BIS.B    #40H,P4OUT                      ; E active                    
           NOP                                      ;           
           NOP                                      ;           
           NOP                                      ; 
           MOV.W    #10,PJOUT                       ; Control10 Data on the bus
           NOP                                      ;            
           NOP                                      ;            
           NOP                                      ;
           BIC.B    #80H,P4OUT                      ; /WRITE active                                 
           NOP                                      ;            
           NOP                                      ;                      
           NOP                                      ;           
           NOP                                      ;           
           NOP                                      ;           
           NOP                                      ;                                                                                                                                      
           BIS.B    #80H,P4OUT                      ; /WRITE inactive                                                               
           BIC.B    #40H,P4OUT                      ; E passive           
           MOV.W    #00,PJOUT                       ; Disconnect all
          

           POP.W    R5                              ; Restore registers           
           POP.W    R4                              ;
           

           MOV.W    0(SP),2(SP)                     ;           
           INCD.W   SP                              ;
           
           
           BIS.B    #03H,P1IE                       ;  Enable P1.0 and P1.1 Interrupt
                                           
           RET                                      ;
           

;-------------------------------------------------------------------------------------------------------------

           END      BusWrite                        ;
           
 
           
           