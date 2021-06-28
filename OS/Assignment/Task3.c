/*************************************************
* Author: Ahmad Allahham                         *
* Filename: Task3.c                              *
* Date: 10/05/2021                               *
*************************************************/
#include "Functions.h"

// Declaration of thread condition variable filenameRead
pthread_cond_t filenameRead = PTHREAD_COND_INITIALIZER;

// Declaration of thread condition variable programsExecuted
pthread_cond_t programsExecuted = PTHREAD_COND_INITIALIZER;

// Declaration of thread condition variable programsExecuted2
pthread_cond_t programsExecuted2 = PTHREAD_COND_INITIALIZER;

// declaring mutex
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

char buffer1[14];
char* buffer2;
int numOfProgramsRan = 0;
char programOutputs[2][1000];
int parentThreadBlocking = FALSE;
int childrenBlocking = FALSE;

int main(void) {
    pthread_t parentThread;
    pthread_t threadA;
    pthread_t threadB;

    // Create threads A and B.
    // If isPPExecuting = TRUE, then PP program runs
    // If isPPExecuting = FALSE, then STRF program runs
    Container* AContainer = (Container*) malloc(sizeof(Container));
    AContainer->isPPExecuting = TRUE;

    Container* BContainer = (Container*) malloc(sizeof(Container));
    BContainer->isPPExecuting = FALSE;

    do {
        parentThreadBlocking = FALSE;

        pthread_create(&threadA, NULL, &threadExecution, (void*) AContainer);
        pthread_create(&threadB, NULL, &threadExecution, (void*) BContainer);

        // sleep for 1 second giving threadA and threadB chance to run first
        sleep(1);

        parentThreadBlocking = TRUE;

        pthread_create(&parentThread, NULL, &threadExecution, NULL);

        // wait for the completion of parentThread
        pthread_join(parentThread, NULL);

        if (strcmp(buffer1, "QUIT") != 0) {
            printf("--------------- Average Turn Around Time And Average Waiting Time For PP and SRTF Programs ---------------\n\n");
            printf("PP: %s\nSRTF: %s\n", programOutputs[0], programOutputs[1]);
            printf("----------------------------------------------------------------------------------------------------------\n");
        }
    } while (strcmp(buffer1, "QUIT") != 0);
    
    return 0;
}

void* threadExecution(void* container) {

    int isPPExecuting;
    if (container != NULL) {
        isPPExecuting = ((Container*) container)->isPPExecuting;
    }

    // acquire a lock
    pthread_mutex_lock(&lock);
    if (!parentThreadBlocking) {
        // let's wait on conition variable filenameRead
        pthread_cond_wait(&filenameRead, &lock);
        if (strcmp(buffer1, "QUIT") != 0) {
            if (isPPExecuting) {
                printf("--------------- PP Gantt Chart ---------------\n");
                buffer2 = runPPProgram(buffer1);
                strcpy(programOutputs[0], buffer2);
                numOfProgramsRan++;
            } else {
                printf("--------------- SRTF Gantt Chart ---------------\n");
                buffer2 = runSRTFProgram(buffer1);
                strcpy(programOutputs[1], buffer2);
                numOfProgramsRan++;
            }

            if (numOfProgramsRan == 2) {
                numOfProgramsRan = 0;
                pthread_cond_signal(&programsExecuted);
                pthread_cond_signal(&programsExecuted2);
            } else {
                pthread_cond_wait(&programsExecuted2, &lock);
            }
        } else {
            if (isPPExecuting) {
                numOfProgramsRan++;
                printf("PP: terminate.\n");
            } else {
                numOfProgramsRan++;
                printf("SRTF: terminate.\n");
            }
            if (numOfProgramsRan == 2) {
                numOfProgramsRan = 0;
                pthread_cond_signal(&programsExecuted);
            }
        }
    } else {
        // Let's signal condition variable filenameRead
        printf("Filename: ");
        scanf("%s", buffer1);

        pthread_cond_broadcast(&filenameRead);

        // make the parentThread wait for both programs to complete
        pthread_cond_wait(&programsExecuted, &lock);

        if (strcmp(buffer1, "QUIT") == 0) {
            printf("Parent Thread: terminate.\n");
        }
    }

    // release locks
    pthread_mutex_unlock(&lock);

    return NULL;
}
