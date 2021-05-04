/*************************************************
* Author: Ahmad Allahham                         *
* Filename: PP.c                                 *
* Purpose:                                       *
* Date: 01/05/2021                               *
*************************************************/
#include "functions.h"

int main(void)
{
    char fileName[10];
    char* programOutput;
    do {
        printf("PP simulation: ");
        scanf("%s", fileName);
        if (strcmp(fileName, "QUIT") != 0) {
            programOutput = runPPProgram(fileName);
            printf("%s", programOutput);
        }
    } while (strcmp(fileName, "QUIT") != 0);
    return 0;
}

char* runPPProgram(char* fileName) {
    char* output = (char*) malloc(1024);
    // open the input file for reading
    FILE* in = fopen(fileName, "r");

    if (in == NULL) {
        perror("Error reading input file");
    } else {
        if (!(isFileEmpty(in))) {
            Heap *readyQueue = CreateHeap(HEAP_SIZE, 0);
            Heap *completedProcesses = CreateHeap(HEAP_SIZE, 0);

            int currentTime = 0;

            Process* newProcess = (Process*) malloc(sizeof(Process));
            Process* processExecuting = (Process*) malloc(sizeof(Process));

            int processCounter = 0;

            while (fscanf(in, "%d %d %d", &newProcess->arrivalTime, &newProcess->burstTime, &newProcess->priority) == 3) {
                newProcess->burstTimeConst = newProcess->burstTime;
                processCounter++;
                newProcess->id = processCounter;

                if (processCounter == 1) {
                    currentTime = newProcess->arrivalTime;
                    parseProcess(processExecuting, newProcess);
                    printf("------ %d\n", currentTime);
                } else {
                    while ((newProcess->arrivalTime - currentTime) >= processExecuting->burstTime && processExecuting->burstTime > 0) {
                        printf("| P%d |\n", processExecuting->id);
                        currentTime += processExecuting->burstTime;
                        printf("------ %d\n", currentTime);
                        processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                        processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                        processExecuting->burstTime = 0;
                        insert_PP(completedProcesses, processExecuting);
                        if (readyQueue->count > 0) {
                            processExecuting = PopMin_PP(readyQueue);
                        }
                    }

                    if (processExecuting->burstTime != 0) {
                        processExecuting->burstTime -= newProcess->arrivalTime - currentTime;
                    }

                    if (processExecuting->priority > newProcess->priority) {
                        if (currentTime < newProcess->arrivalTime) {
                            printf("| P%d |\n", processExecuting->id);
                            printf("------ %d\n", newProcess->arrivalTime);
                        }
                        if (processExecuting->burstTime != 0) {
                            insert_PP(readyQueue, processExecuting);
                        } else {
                            processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                            processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                            insert_PP(completedProcesses, processExecuting);
                        }
                        parseProcess(processExecuting, newProcess);
                    } else {
                        insert_PP(readyQueue, newProcess);
                    }

                    currentTime = newProcess->arrivalTime;
                }
            }

            /* print remaining processes in readyQueue */
            if (processExecuting->burstTime > 0) {
                currentTime += processExecuting->burstTime;
                printf("| P%d |\n", processExecuting->id);
                printf("------ %d\n", currentTime);
                processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                insert_PP(completedProcesses, processExecuting);
            }
            
            while (readyQueue->count > 0) {
                processExecuting = PopMin_PP(readyQueue);
                printf("| P%d |\n", processExecuting->id);
                currentTime += processExecuting->burstTime;
                printf("------ %d\n", currentTime);
                processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                insert_PP(completedProcesses, processExecuting);
            }

            // print average turnAroundTime and average waitingTime
            double averageTurnAroundTime = 0;
            double averageWaitingTime = 0;
            while (completedProcesses->count > 0) {
                newProcess = PopMin_PP(completedProcesses);
                averageTurnAroundTime += (double) newProcess->turnAroundTime;
                averageWaitingTime += (double) newProcess->waitingTime;
            }

            averageTurnAroundTime /= processCounter;
            averageWaitingTime /= processCounter;

            snprintf(output, 1024, "the average turnaround time = %f, the average waiting time: %f\n", averageTurnAroundTime, averageWaitingTime);
        }

        if (ferror(in)) {
            perror("Error reading from file");
        }

        fclose(in);
    }

    return output;
}