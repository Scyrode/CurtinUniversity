#include "functions.h"

// Declaration of thread condition variable filenameRead
pthread_cond_t filenameRead = PTHREAD_COND_INITIALIZER;

// Declaration of thread condition variable programsExecuted
pthread_cond_t programsExecuted = PTHREAD_COND_INITIALIZER;

// declaring mutex
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

// declaring mutex
pthread_mutex_t lock2 = PTHREAD_MUTEX_INITIALIZER;

char* buffer1;
char* buffer2;
int numOfProgramsRan = 0;
char** programOutputs;
int parentThreadBlocking = FALSE;
int childrenBlocking = FALSE;

int main(void) {
    pthread_t parentThread;
    pthread_t threadA;
    pthread_t threadB;

    programOutputs = (char**) malloc(sizeof(char*)*2);

    do {
        parentThreadBlocking = FALSE;
        
        // Create threads A and B.
        // If PPExecuting = TRUE, then PP program runs
        // If PPExecuting = FALSE, then STRF program runs
        pthread_create(&threadA, NULL, threadExecution, TRUE);
        pthread_create(&threadB, NULL, threadExecution, FALSE);

        // sleep for 1 second giving threadA and threadB chance to run first
        sleep(1);

        parentThreadBlocking = TRUE;

        pthread_create(&parentThread, NULL, threadExecution, NULL);

        // wait for the completion of parentThread
        pthread_join(parentThread, NULL);

        printf("PP: %s\nSRTF: %s\n", programOutputs[0], programOutputs[1]);
    } while (strcmp(buffer1, "QUIT") != 0);
    
    return 0;
}

void threadExecution(int PPExecuting) {
    // acquire a lock
    pthread_mutex_lock(&lock);
    if (!parentThreadBlocking) {
        // let's wait on conition variable filenameRead
        printf("Waiting on condition variable filenameRead\n");
        pthread_cond_wait(&filenameRead, &lock);
        pthread_mutex_lock(&lock2);
        if (strcmp(buffer1, "QUIT") != 0) {
            if (PPExecuting) {
                buffer2 = runPPProgram(buffer1);
                strcpy(programOutputs[0], buffer2);
                numOfProgramsRan++;
            } else {
                buffer2 = runSRTFProgram(buffer1);
                strcpy(programOutputs[1], buffer2);
                numOfProgramsRan++;
            }

            if (numOfProgramsRan == 2) {
                numOfProgramsRan = 0;
                pthread_cond_signal(&programsExecuted);
            }
        }
    } else {
        // Let's signal condition variable filenameRead
        printf("Signaling condition variable filenameRead\n");
        scanf("%s", buffer1);
        pthread_cond_signal(&filenameRead);

        // make the parentThread wait for one of the programs to
        // complete
        pthread_cond_wait(&programsExecuted, &lock);
    }

    // release locks
    pthread_mutex_unlock(&lock);
    pthread_mutex_unlock(&lock2);

    printf("Returning thread\n");

    return NULL;
}