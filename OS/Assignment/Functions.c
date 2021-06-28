/*************************************************
* Author: Ahmad Allahham                         *
* Filename: Functions.c                          *
* Date: 10/05/2021                               *
*************************************************/
#include "Functions.h"

/*************************************************
* Author: Sudhanshu Patel                        *
* Function: CreateHeap, insert (modified to      *
* 'insert_PP' && 'insert_SRTF'),                 *
* heapify_bottom_top (modified to                *
* 'heapify_bottom_top_PP' and                    *
* 'heapify_top_bottom_SRTF'),                    *
* PopMin (modified to 'PopMin_PP' and            *
* 'PopMin_SRTF'),                                *
* Email: sudhanshuptl13@gmail.com                *
* Date: 10/05/2021                               *
*************************************************/
/* Sudhanshu Patel  */
/*
Min Heap implementation in c
*/
Heap *CreateHeap(int capacity,int heap_type){
    Heap *h = (Heap * ) malloc(sizeof(Heap)); //one is number of heap

    //check if memory allocation is fails
    if(h == NULL){
        printf("Memory Error!");
        return;
    }
    h->heap_type = heap_type;
    h->count=0;
    h->capacity = capacity;
    h->arr = (Process **) malloc(capacity*sizeof(Process*)); //size in bytes

    //check if allocation succeed
    if ( h->arr == NULL){
        printf("Memory Error!");
        return;
    }
    return h;
}

/* ----------------------PP Functions----------------------- */

void insert_PP(Heap *h, Process* process){
    if( h->count < h->capacity){
        h->arr[h->count] = (Process*) malloc(sizeof(Process));
        parseProcess(h->arr[h->count], process);
        heapify_bottom_top_PP(h, h->count);
        h->count++;
    }
}

void heapify_bottom_top_PP(Heap *h,int index){
    Process* temp;
    int parent_node = (index-1)/2;

    if(h->arr[parent_node]->priority > h->arr[index]->priority){
        //swap and recursive call
        temp = h->arr[parent_node];
        h->arr[parent_node] = h->arr[index];
        h->arr[index] = temp;
        heapify_bottom_top_PP(h,parent_node);
    }
}

void heapify_top_bottom_PP(Heap *h, int parent_node){
    int left = parent_node*2+1;
    int right = parent_node*2+2;
    int min;
    Process* temp;

    if(left >= h->count || left <0)
        left = -1;
    if(right >= h->count || right <0)
        right = -1;

    if(left != -1 && h->arr[left]->priority < h->arr[parent_node]->priority)
        min=left;
    else
        min =parent_node;
    if(right != -1 && h->arr[right]->priority < h->arr[min]->priority)
        min = right;

    if(min != parent_node){
        temp = h->arr[min];
        h->arr[min] = h->arr[parent_node];
        h->arr[parent_node] = temp;

        // recursive  call
        heapify_top_bottom_PP(h, min);
    }
}

Process* PopMin_PP(Heap *h){
    Process* pop;
    if(h->count==0){
        printf("\n__Heap is Empty__\n");
        return NULL;
    }
    // replace first node by last and delete last
    pop = h->arr[0];
    h->arr[0] = h->arr[h->count-1];
    h->count--;
    heapify_top_bottom_PP(h, 0);
    return pop;
}

/* ----------------------SRTF Functions----------------------- */

void insert_SRTF(Heap *h, Process* process){
    if( h->count < h->capacity){
        h->arr[h->count] = (Process*) malloc(sizeof(Process));
        parseProcess(h->arr[h->count], process);
        heapify_bottom_top_SRTF(h, h->count);
        h->count++;
    }
}

void heapify_bottom_top_SRTF(Heap *h,int index){
    Process* temp;
    int parent_node = (index-1)/2;

    if(h->arr[parent_node]->burstTime > h->arr[index]->burstTime){
        //swap and recursive call
        temp = h->arr[parent_node];
        h->arr[parent_node] = h->arr[index];
        h->arr[index] = temp;
        heapify_bottom_top_SRTF(h,parent_node);
    }
}

void heapify_top_bottom_SRTF(Heap *h, int parent_node){
    int left = parent_node*2+1;
    int right = parent_node*2+2;
    int min;
    Process* temp;

    if(left >= h->count || left <0)
        left = -1;
    if(right >= h->count || right <0)
        right = -1;

    if(left != -1 && h->arr[left]->burstTime < h->arr[parent_node]->burstTime)
        min=left;
    else
        min =parent_node;
    if(right != -1 && h->arr[right]->burstTime < h->arr[min]->burstTime)
        min = right;

    if(min != parent_node){
        temp = h->arr[min];
        h->arr[min] = h->arr[parent_node];
        h->arr[parent_node] = temp;

        // recursive  call
        heapify_top_bottom_SRTF(h, min);
    }
}

Process* PopMin_SRTF(Heap *h){
    Process* pop;
    if(h->count==0){
        printf("\n__Heap is Empty__\n");
        return NULL;
    }
    // replace first node by last and delete last
    pop = h->arr[0];
    h->arr[0] = h->arr[h->count-1];
    h->count--;
    heapify_top_bottom_SRTF(h, 0);
    return pop;
}

/* ----------------------Utility Functions----------------------- */

int isFileEmpty(FILE* in) {

    int ch;
    int result;

    ch = fgetc(in);

    if (ch == EOF) {
	    result = TRUE;
    } else {
	    fseek(in, 0, SEEK_SET);
	    result = FALSE;
    }

    return  result;
}

void parseProcess(Process* processToBeUpdated, Process* newProcess) {
    processToBeUpdated->arrivalTime = newProcess->arrivalTime;
    processToBeUpdated->burstTime = newProcess->burstTime;
    processToBeUpdated->burstTimeConst = newProcess->burstTimeConst;
    processToBeUpdated->priority = newProcess->priority;
    processToBeUpdated->id = newProcess->id;
    processToBeUpdated->turnAroundTime = newProcess->turnAroundTime;
    processToBeUpdated->waitingTime = newProcess->waitingTime;
}

/* ----------------------PP Program----------------------- */

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

/* ----------------------SRTF Program----------------------- */

char* runSRTFProgram(char* fileName) {
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
                        insert_SRTF(completedProcesses, processExecuting);
                        if (readyQueue->count > 0) {
                            processExecuting = PopMin_SRTF(readyQueue);
                        }
                    }

                    if (processExecuting->burstTime != 0) {
                        processExecuting->burstTime -= newProcess->arrivalTime - currentTime;
                    }

                    if (processExecuting->burstTime > newProcess->burstTime) {
                        if (currentTime < newProcess->arrivalTime) {
                            printf("| P%d |\n", processExecuting->id);
                            printf("------ %d\n", newProcess->arrivalTime);
                        }
                        if (processExecuting->burstTime != 0) {
                            insert_SRTF(readyQueue, processExecuting);
                        } else {
                            processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                            processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                            insert_SRTF(completedProcesses, processExecuting);
                        }
                        parseProcess(processExecuting, newProcess);
                    } else {
                        insert_SRTF(readyQueue, newProcess);
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
                insert_SRTF(completedProcesses, processExecuting);
            }
            
            while (readyQueue->count > 0) {
                processExecuting = PopMin_SRTF(readyQueue);
                printf("| P%d |\n", processExecuting->id);
                currentTime += processExecuting->burstTime;
                printf("------ %d\n", currentTime);
                processExecuting->turnAroundTime = currentTime - processExecuting->arrivalTime;
                processExecuting->waitingTime = processExecuting->turnAroundTime - processExecuting->burstTimeConst;
                insert_SRTF(completedProcesses, processExecuting);
            }

            // print average turnAroundTime and average waitingTime
            double averageTurnAroundTime = 0;
            double averageWaitingTime = 0;
            while (completedProcesses->count > 0) {
                newProcess = PopMin_SRTF(completedProcesses);
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