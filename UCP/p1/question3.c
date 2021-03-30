#include <stdio.h>

int factorial(int n);

int main(void)
{
    int result, n;

    printf("enter a number that you would like to find the factorial of: ");
    scanf("%d", &n);

    while(n < 0) 
    {
        printf("number must be greater or equal to 0\n");
        printf("enter a number that you would like to find the factorial of: ");
        scanf("%d", &n);
    }
   
    result = factorial(n);
    printf("%d factorial = %d\n", n, result);

    return 0;
}

int factorial(int n)
{
    int i;
    int result = 1;

    for(i = 1; i <= n; i++)
    {
        result *= i;
    }

    return result;
}
