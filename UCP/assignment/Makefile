CC = gcc
CFLAGS = -Wall -pedantic -ansi -g
OBJ = turtleGraphics.o functions.o effects.o
EXEC = turtleGraphics

all: $(EXEC)

debug: CC += -D TURTLEGRAPHICSDEBUG=1
debug: $(EXEC)

simple: CC += -D TURTLEGRAPHICSSIMPLE=1
simple: $(EXEC)

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC) -lm

turtleGraphics.o : turtleGraphics.c turtleGraphics.h functions.h effects.h
	$(CC) $(CFLAGS) -c turtleGraphics.c

functions.o : functions.c functions.h
	$(CC) $(CFLAGS) -c functions.c

effects.o : effects.c effects.h
	$(CC) $(CFLAGS) -c effects.c

clean :
	rm -f $(EXEC) $(OBJ)
