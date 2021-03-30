#define ELAPSED_TIME(currentTime, startTime) ((currentTime) - (startTime))

#define PERCENT_COMPLETE(bytes, totalBytes) ((bytes) / (totalBytes) * (100))

#define DOWNLOAD_SPEED(currentTime, startTime, bytes) ((bytes) / ((currentTime) - startTime))

#define TOTAL_TIME(currentTime, startTime, bytes, totalBytes) ((((currentTime) - (startTime)) / (bytes)) * (totalBytes))

#define REMAINING_TIME(currentTime, startTime, bytes, totalBytes) (((((currentTime) - (startTime)) / (bytes)) * (totalBytes)) - ((currentTime) - (startTime)))

