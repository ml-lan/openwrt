#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/param.h>
#include <sys/types.h>
#include <sys/stat.h>


void init_daemon()
{
    int pid;
    int i;
    pid = fork();
    if(pid<0)
    {
        exit(1);
    }
    else if(pid>0)
    {
        exit(0);
    }
    setsid();
    pid = fork();
    if(pid>0)
    {
        exit(0);
    }
    else if(pid<0)
    {
        exit(1);
    }
    for(i=0;i<NOFILE;i++)
    {
        close(i);
    }
    chdir("/root");
    umask(0);
    return;
}

