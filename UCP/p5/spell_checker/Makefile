CC = gcc
CFLAGS = -ansi -pedantic -g
OBJ = spellChecker.o input.o output.o check.o
DEF = -DEXTRAS_OFF=1
EXEC = check

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

spellChecker.o : spellChecker.c spellChecker.h input.h output.h check.h
	$(CC) -c spellChecker.c $(CFLAGS)

input.o : input.c spellChecker.h input.h
	$(CC) -c input.c $(CFLAGS)

output.o : output.c spellChecker.h output.h check.h
	$(CC) -c output.c $(CFLAGS)

output_NE.o : output.c spellChecker.h output.h check.h
	$(CC) -c output.c $(CFLAGS) -o output_NE.o $(DEF)

check.o : check.c check.h
	$(CC) -c check.c $(CFLAGS)

clean :
	rm -f $(OBJ) $(OBJ_NE) $(EXEC) $(NOEXTRAS)

run :
	./$(EXEC) test.txt output.txt

runVal :
	valgrind ./$(EXEC) test.txt output.txt
