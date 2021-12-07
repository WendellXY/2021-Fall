;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; SECTION NUMBER:
; W04
; GROUP MEMBER NAMES:
; 1. Wang Xinyu (Wendell) 1098648
; 2. hello
; 3.
; 4.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;     Register Usage
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  R0- x-cordinate for Draw_Block Trap or Trap x40 instrction
;  R1- y-cordinate for Draw_Block Trap or Trap x40 instrction
;  R2 - Global X-coordinate; start point for displaying characters - DONOT TAMPER for local use
;  R3 - Address in memory where bitmap for character is present
;  R4
;  R5
;  R6
;  R7
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;     Memory Locations being used
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;  SCROLL_LOOP_COUNTER: memory location for storing current value for scroll loop counter
;  COLUMN_LOOP_COUNTER: memory location for storing current value for column loop counter
;  ROW_LOOP_COUNTER: memory location for storing current value for row loop counter
;  BITMAP_A: Stores the address from where the bitmap of A starts (Look into hw6_bitmap.asm)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Hints to get you started:
; look for TODOs corresponding to each task in the code and you need to fill in few instructions as per the comment
; Test after adding few lines of code and see if it behaves the you want it to
; You should be using lc3os_mod.asm as your OS
; You must load bitmap file as file before running the program
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;   program start
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    .ORIG x3000   ; Start program at x3000
START:
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ; clear frame buffer on start
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ; R5 is the pointer to the where the pixels will be written to the display
    LD R5,VIDEO
    ; Black pixel value
    LD R6,BLACK
    ; The size of the entire frame buffer (We are going to write the value of BLACK in the entire frame buffer)
    LD R3,TOT_SIZE
INIT_FRAME_LOOP:
    ;I repeat this same operation 8 times, that way we can speed up the overall loop
    ;(8 writes/11 instructions instead of 1 write/4 instructions)
    STR R6,R5,#0    ; In this loop, we are storing the pixel in the appropriate location, then incrementing R5
    STR R6,R5,#1
    STR R6,R5,#2
    STR R6,R5,#3
    STR R6,R5,#4
    STR R6,R5,#5
    STR R6,R5,#6
    STR R6,R5,#7
    ADD R5,R5,#8     ;increment R5 by 8
    ADD R3,R3,#-8    ;subtracting 8 because this operation is done 8 times.
    BRp INIT_FRAME_LOOP

    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ; end of init - initialize GLOBAL X and SCROLL_LOOP_COUNTER
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ; Initial start point -- GLOBAL X
    LD  R2, X_START

    ; TODO (part 3): initialize SCROLL_LOOP_COUNTER memory location to 10
    LD  R5, SCROLL_LOOP_COUNTER
    ADD R5, R5, #10
    ST  R5, SCROLL_LOOP_COUNTER
SCROLL_LOOP:
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ; clear the display area
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    LD R6,BLACK    ; Black pixel value
    LD R3,DISPSIZE ; The size of the entire frame buffer (We are going to write the value of BLACK in the entire frame buffer)

    LD R5, VIDEO    ; R5 is the pointer to the where the pixels will be written to the display
    LD R0, Y_START  ;
    LD R1, FOUR_ROW_WIDTH
    ; Loop for updating R5 to coordinate (0, Y_START)
SET_Y_OFFSET:
    ADD R5, R5, R1
    ADD R0, R0, #-1
    BRp SET_Y_OFFSET

    ; This loop is similar to init_frame_loop but clears only rows 6-13
CLR_DISPLAY_LOOP0:
    STR R6,R5,#0    ;I repeat this same operation 8 times, that way we can speed up the overall loop (8 writes/11 instructions instead of 1 write/4 instructions)
    STR R6,R5,#1
    STR R6,R5,#2
    STR R6,R5,#3
    STR R6,R5,#4
    STR R6,R5,#5
    STR R6,R5,#6
    STR R6,R5,#7
    ADD R5,R5,#8
    ADD R3,R3,#-8    ; Checking to see if the entire frame buffer has been written into
    BRp CLR_DISPLAY_LOOP0    ;subtracting 8 because this operation is done 8 times.


    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ;   first char
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ; Update X for first character -- local X
    ; same as GLOBAL X when starting fresh
    AND R0, R0, #0
    ADD R0, R2, #0
    ; Update Y for first character
    LD  R1, Y_START
    ; Put the address of Bitmap for char to be drawn
    LD  R3, BITMAP_CHAR_A

    ; Load value BITMAP_WIDTH
    LD R6, BITMAP_WIDTH
    ; initialize ROW_LOOP_COUNTER to BITMAP_WIDTH
    ST R6, ROW_LOOP_COUNTER
ROW_LOOP0:
    ; Load value BITMAP_WIDTH
    LD  R4, BITMAP_WIDTH
    ; initialize COLUMN_LOOP_COUNTER to BITMAP_WIDTH
    ST  R4, COLUMN_LOOP_COUNTER
COLUMN_LOOP0:
    ; TODO (part 1): load the bitmap value of current block to R4
    LDR R4, R3, #0
    ; TODO (part 1): call conditional "TRAP 0x40" - dependent on value read into R4
    BRz CONDITIONAL_TRAP0
    TRAP x40
CONDITIONAL_TRAP0:
    ; TODO (part 1): increment x
    ADD R0, R0, #1
    ; TODO (part 1): increment R3 to go to next location which contains bitmap for next element
    ADD R3, R3, #1
    ; TODO (part 1): Load COLUMN_LOOP_COUNTER value from memory
    LD R4, COLUMN_LOOP_COUNTER
    ; TODO (part 1): decrement row loop count
    ADD R4, R4, #-1
    ; TODO (part 1): Update COLUMN_LOOP_COUNTER in memory
    ST R4, COLUMN_LOOP_COUNTER
    ; branch to column loop if positive
    BRp COLUMN_LOOP0

    ; increment Y i.e. Y = Y + 1
    ADD R1, R1, #1
    ; decrement X by BITMAP_WIDTH i.e. X = X-7
    LD  R4, NEG_BITMAP_WIDTH
    ADD R0, R0, R4

    ; Load ROW_LOOP_COUNTER value from memory
    LD  R6, ROW_LOOP_COUNTER
    ; decrement the counter; counter = counter - 1
    ADD R6, R6, #-1
    ; Store into memory
    ST  R6, ROW_LOOP_COUNTER
    ; branch to row loop if positive
    BRp ROW_LOOP0
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ;   end of first char
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ;   second char
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;     TODO (part 2): Update X (i.e. R0) for next char
;     Note that the characters don't overlap with each other
    LD  R4, BITMAP_WIDTH
    ADD R0, R0, R4
    LD  R4, SPACE_BETWEEN_CHAR
    ADD R0, R0, R4
    ; TODO (part 2): Update Y (i.e R1) for next char -- should be same as first char (why?)
    LD  R1, Y_START
    ; Update Bitmap Address for next char -- TODO (part 2): add label BITMAP_CHAR_B below similar to that of A
    ; Uncomment the line below when you get to this part
    ; commenting now to make it compile
    ; LD  R3, BITMAP_CHAR_B

    ; TODO (part 2) initialize row loop counter to BITMAP_WIDTH
    LD R6, BITMAP_WIDTH
    ST R6, ROW_LOOP_COUNTER

    ; TODO (part 2): Write ROW_LOOP and COLUMN_LOOP for displaying second char similar to first char
ROW_LOOP1:
    ; actually identical to the ROW_LOOP0
    LD  R4, BITMAP_WIDTH
    ST  R4, COLUMN_LOOP_COUNTER
COLUMN_LOOP1:
    ; copy and paste from the COLUMN_LOOP0 and replacing CONDITIONAL_TRAP0 with CONDITIONAL_TRAP1
    LDR R4, R3, #0
    BRz CONDITIONAL_TRAP1
    TRAP x40
CONDITIONAL_TRAP1:
    ; copy and paste from the CONDITIONAL_TRAP0 and replacing COLUMN_LOOP0 with COLUMN_LOOP1
    ADD R0, R0, #1
    ADD R3, R3, #1
    LD R4, COLUMN_LOOP_COUNTER
    ADD R4, R4, #-1
    ST R4, COLUMN_LOOP_COUNTER
    BRp COLUMN_LOOP1
    ADD R1, R1, #1
    LD  R4, NEG_BITMAP_WIDTH
    ADD R0, R0, R4
    LD  R6, ROW_LOOP_COUNTER
    ADD R6, R6, #-1
    ST  R6, ROW_LOOP_COUNTER
    BRp ROW_LOOP1
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
    ; end of second char
    ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;     ; TODO (part 3): update GLOBAL X -- for next iteration of scroll loop
;     ADD R2, R2, #-1

;     ;;;; wait for some time
;     LD  R0, SPIN_TIME
SPIN0:
    ADD R0, R0, #-1
    BRp SPIN0

    LD  R0, SPIN_TIME
SPIN1:
    ADD R0, R0, #-1
    BRp SPIN1

    ; TODO (part 3): Load value of SCROLL_LOOP_COUNTER from memory
    LD  R5, SCROLL_LOOP_COUNTER
    ; TODO (part 3): Decrement the value by 1
    ADD R5, R5, #-1
    ; TODO (part 3): Store the SCROLL_LOOP_COUNTER in memory
    ST  R5, SCROLL_LOOP_COUNTER
    ; TODO (part 3): Branch to SCROLL_LOOP if SCROLL_LOOP_COUNTER is positive
    BRp SCROLL_LOOP

    ; stop when done
    HALT

SPIN_TIME: .FILL 32767

; start from row - 7 i.e. Y = 6
Y_START:            .FILL 6
; global start for X i.e. X = 1
X_START:            .FILL 1
; Space between 2 characters - 1 block
SPACE_BETWEEN_CHAR: .FILL 1
; Space taken by each character
CHAR_LEN:           .FILL 7
; Counter for SCROLL_LOOP
SCROLL_LOOP_COUNTER: .FILL 0
; Counter for COLUMN_LOOP
COLUMN_LOOP_COUNTER: .FILL 0
; Counter for ROW_LOOP
ROW_LOOP_COUNTER:   .FILL 0

; Start Address of Bitmap of A in memory
BITMAP_CHAR_A:      .FILL 0x5000

;; TODO (part 2): FILL IN ADDRESS for BITMAP for B here
BITMAP_CHAR_B:      .FILL 0x5031

;;; assuming 7x7 bitmap available row wise
BITMAP_WIDTH:       .FILL 7
NEG_BITMAP_WIDTH:   .FILL -7

VIDEO:              .FILL 0xC000
; color value for BLUE
COLOR:              .FILL 0x001F
; color value for BLACK
BLACK:              .FILL 0x0000
; color value for WHITE
WHITE:              .FILL 0xffff
DISPSIZE:           .FILL 0xE00
TOT_SIZE:           .FILL 0x3E00
ROW_WIDTH:          .FILL 0x80
FOUR_ROW_WIDTH:     .FILL 0x200

;  Memory locations for register saving
DB_R0: .FILL 0x0
DB_R1: .FILL 0x0
DB_R2: .FILL 0x0
DB_R3: .FILL 0x0
DB_R4: .FILL 0x0
DB_R5: .FILL 0x0
DB_R6: .FILL 0x0
DB_R7: .FILL 0x0


    .END
