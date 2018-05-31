// Shared memory interface
// using shared memory to implement a chat application
// User 1
#include <sys/shm.h>
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main (int argc, char* argv[]){
	int seg_id, seg_id1, *turn, myId=1;
	char *preMsg, *curMsg;

	seg_id = shmget (10, sizeof(char) * 1024, IPC_CREAT | 0666);
	seg_id1 = shmget (11, sizeof(int), IPC_CREAT | 0666);
	if (seg_id == -1) {
		printf ("Fail to create memory segment");
		exit (1);
	}
	
	*turn = 0;
	turn = (int *)shmat (seg_id1, NULL, 0);
	curMsg = (char *)shmat (seg_id, NULL, 0);
	if (curMsg == (char *) -1) {
		printf ("Fail to attach");
		exit (1);
	}
	
	while(1){
		if(*turn!=myId){
			printf("You: ");
			fgets(curMsg, 1024, stdin);
			strcpy(preMsg, curMsg);
			*turn = myId;	
		} else{
			if(strcmp(preMsg, curMsg)){
				printf("Friend: ");
				puts(curMsg);
				strcpy(preMsg, curMsg);
			}
		}
	}
	
	shmdt (curMsg);

	shmctl (seg_id, IPC_RMID, 0);
	return 0;
}
