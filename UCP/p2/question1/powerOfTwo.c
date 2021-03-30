#include "powerOfTwo.h"

int main(void)
{
    int i = 0;
    int result = 0;
    while (i == 0)
    {

        printf("would you like to get the next power of two?\n(write 0 for yes, 1 for no)\n");
        scanf("%d", &i);
        if (i == 0)
        {
            result = powerOfTwo();
            printf("the next power of two is: %d\n", result);
        }
    }
    return 0;
}

int powerOfTwo(void)
{
    static int power = 1;
    power *= 2;
    return power;
}
