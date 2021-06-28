/*************************************************
* Author: Ahmad Allahham                         *
* Filename: Functions.h                          *
* Date: 10/05/2021                               *
*************************************************/
#include<stdio.h>
#include<stdlib.h>
#include <math.h>
#include <string.h>
#include <pthread.h>
#include <unistd.h>


#define HEAP_SIZE 20
#define FALSE 0
#define TRUE !FALSE

/* structure for a process */
typedef struct {
    int arrivalTime;
    int burstTime;
    int burstTimeConst;
    int priority;
    int turnAroundTime;
    int waitingTime;
    int id;
} Process;

typedef struct {
    Process **arr;
    int count;
    int capacity;
    int heap_type; // for min heap , 1 for max heap
} Heap;

typedef struct {
    int isPPExecuting;
} Container;

Heap *CreateHeap(int capacity,int heap_type);
void insert_PP(Heap *h, Process* process);
void heapify_bottom_top_PP(Heap *h,int index);
void heapify_top_bottom_PP(Heap *h, int parent_node);
Process* PopMin_PP(Heap *h);
void insert_SRTF(Heap *h, Process* process);
void heapify_bottom_top_SRTF(Heap *h,int index);
void heapify_top_bottom_SRTF(Heap *h, int parent_node);
Process* PopMin_SRTF(Heap *h);
void print(Heap *h);

int isFileEmpty(FILE* in);
void parseProcess(Process* processExecuting, Process* newProcess);

char* runPPProgram(char* fileName);
char* runSRTFProgram(char* fileName);

void* threadExecution(void* PPExecuting); 
