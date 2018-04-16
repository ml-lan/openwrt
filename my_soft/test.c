#include <stdio.h>  
#include <stdlib.h>  
#include <unistd.h>  
#include <sys/socket.h>  
#include <bluetooth/bluetooth.h>                                                                                                            
#include <bluetooth/rfcomm.h>  
  
int main(int argc,char **argv)  
{  
        struct sockaddr_rc addr={0};  
        int s,status;  
        char *dest,*buf;  
  
       if(argc==2)  
        {  
                dest=argv[1];  
       }  
       else  
       {  
               printf("Pram error\n");  
                exit(1);  
         }  
   
         printf("Creat socket!\n");  
        s=socket(PF_BLUETOOTH,SOCK_STREAM,BTPROTO_RFCOMM);  
         if(s<0)  
        {  
                printf("creat socket error!!\n");  
                exit(1);  
         }  
   
         buf=(char*)malloc(sizeof(char)*128);  
         addr.rc_family = AF_BLUETOOTH;  
         addr.rc_channel = (uint8_t)1;  
        str2ba(dest,&addr.rc_bdaddr);  
   
         printf("connectting...\n");  
         status=connect(s,(struct sockaddr *)&addr,sizeof(addr));  
         if(status==0)  
         {  
                 printf("scuess!\n");  
                 status=write(s,"A",1);  
                printf("If you want to exit,please input : goodbye\n");  
                 printf("Please input:)\n");  
                 do{  
                         scanf("%s",buf);  
                         status=write(s,buf,strlen(buf));  
                        if(status<0) perror("uh oh");  
                         printf("Please input:)\n");  
                 }while(strcmp(buf,"goodbye")!=0);  
                 printf("You have exit!\n");  
         }  
         else  
         {  
                 printf("Connect Failed!\n");  
         }  
         free(buf);  
         close(s);  
         return 0;  
  
 } 