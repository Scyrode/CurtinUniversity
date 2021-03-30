CC = gcc
CFLAGS = -Wall -pedantic -ansi -g
OBJ = ArrayFunction.o Functions.o
EXEC = prog

$(EXEC): $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

ArrayFunction.o: ArrayFunction.c ArrayFunction.h Functions.h
	$(CC) $(CFLAGS) ArrayFunction.c -c 

Functions.o: Functions.c Functions.h
	$(CC) $(CFLAGS) Functions.c -c 

clean:
	rm -f $(OBJ)
