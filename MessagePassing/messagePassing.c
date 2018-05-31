// Message passing interface
// Child process uses pipe to send data to parent process

#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#define READ 0
#define WRITE 1

void main(){
	char buf[1024] ;
	int fd[2];
	pipe(fd);
	
	if(fork()==0){	// child
		char *message = "This is a message!!!" ;
		write(fd[WRITE], message, strlen(message)+1);
	} else{
		read(fd[READ], buf, 1024);
		printf("Got this from child: %s\n", buf);
	}
}
