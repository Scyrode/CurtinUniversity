CC = gcc
CFLAGS = -Wall -ansi -pedantic -g
OBJ1 = display.o plot.o randomarray.o
OBJ2 = generate.o plot.o randomarray.o
EXEC1 = display
EXEC2 = generate

all : $(EXEC1) $(EXEC2)

$(EXEC1) : $(OBJ1)
	$(CC) $(OBJ1) -o $(EXEC1)

$(EXEC2) : $(OBJ2)
	$(CC) $(OBJ2) -o $(EXEC2)

display.o : display.c display.h plot.h
	$(CC) $(CFLAGS) -c display.c 

plot.o : plot.c plot.h
	$(CC) $(CFLAGS) -c plot.c

randomarray.o : randomarray.c randomarray.h plot.h
	$(CC) $(CFLAGS) -c randomarray.c

generate.0 : generate.c generate.h randomarray.h
	$(CC) $(CFLAGS) -c generate.c

clean :
	rm -f $(EXEC1) $(EXEC2) $(OBJ1) $(OBJ2)
