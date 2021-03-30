CC = gcc
CFLAGS = -Wall -pedantic -ansi
OBJ = download.o download_stats.o
EXEC = download

$(EXEC): $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

download.o: download.c
	$(CC) $(CFLAGS) -c download.c

download_stats.o: download_stats.c download_stats.h
	$(CC) $(CFLAGS) -c download_stats.c

clean:
	rm -f $(EXEC) $(OBJ)
