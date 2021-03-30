#include <stdio.h>

int elapsedTime(int currentTime, int startTime);

float percentComplete(double bytes, double totalBytes);

float downloadSpeed(int currentTime, int startTime, double bytes);

float totalTime(int currentTime, int startTime, double bytes, double totalBytes);

float remainingTime(int currentTime, int startTime, double bytes, double totalBytes);
