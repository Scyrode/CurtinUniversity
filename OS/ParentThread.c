#include "functions.h"

// Declaration of thread condition variable
pthread_cond_t cond1 = PTHREAD_COND_INITIALIZER;

// declaring mutex
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;

char* buffer1;
int parentThreadBlocking = FALSE;
int childrenBlocking = FALSE;

int main(void) {
    pthread_t parentThread;
    pthread_t threadA;
    pthread_t threadB;

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
    // pthread_join(parentThread, NULL);

    /* TODO:
    - Block parentThread until output from threadA and threadB is available (buffer2)
        [NOTE]: Buffer2 can only store the result from EITHER threadA or threadB. Do
        not set it the outputs are appended and then parentThread is called, but instead
        parentThread should be called when either threadA or threadB finished executing
    - loop program until user input = "QUIT"
    */
    
    return 0;
}

void threadExecution(int PPExecuting) {
    // acquire a lock
    pthread_mutex_lock(&lock);
    if (!parentThreadBlocking) {
        // let's wait on conition variable cond1
        printf("Waiting on condition variable cond1\n");
        pthread_cond_wait(&cond1, &lock);
        if (strcmp(buffer1, "QUIT") != 0) {
            if (PPExecuting) {
                runPPProgram(buffer1);
            } else {
                runSRTFProgram(buffer1);
            }
        }
    } else {
        // Let's signal condition variable cond1
        printf("Signaling condition variable cond1\n");
        scanf("%s", buffer1);
        pthread_cond_signal(&cond1);
    }

    // release lock
    pthread_mutex_unlock(&lock);

    printf("Returning thread\n");

    return NULL;
}