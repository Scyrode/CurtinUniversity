#include "timing.h"

int main(void) {

    sleepFunction(10);
    printf("waited 10 seconds\n");

    return 0;

}

void sleepFunction(int seconds) {

    int currentTime = time(NULL);

    do {

    } while (time(NULL) != (currentTime + seconds));

}
