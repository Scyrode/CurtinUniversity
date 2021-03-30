#include "download_stats.h"

int elapsedTime(int currentTime, int startTime)
{
    int result = ((currentTime) - (startTime));
    return result;
}

float percentComplete(double bytes, double totalBytes)
{
    float result = ((bytes) / (totalBytes) * (100));
    return result;
}

float downloadSpeed(int currentTime, int startTime, double bytes)
{
    float result = ((bytes) / ((currentTime) - startTime));
    return result;
}

float totalTime(int currentTime, int startTime, double bytes, double totalBytes)
{
    float result = ((((currentTime) - (startTime)) / (bytes)) * (totalBytes));
    return result;
}

float remainingTime(int currentTime, int startTime, double bytes, double totalBytes)
{
    float result = (((((currentTime) - (startTime)) / (bytes)) * (totalBytes)) - ((currentTime) - (startTime)));
    return result;
}
