CC = gcc
CFLAGS = -Wall -pedantic -ansi
OBJ = numbers.o order.o user_input.o
EXEC = prog

$(EXEC): $(OBJ)
	$(CC) $(OBJ) -g -o $(EXEC)

numbers.o: numbers.c numbers.h order.h user_input.h
	$(CC) $(CFLAGS) -c numbers.c

order.o: order.c order.h
	$(CC) $(CFLAGS) -c order.c

user_input.o: user_input.c user_input.h
	$(CC) $(CFLAGS) -c user_input.c

clean:
	rm -f $(EXEC) $(OBJ)
