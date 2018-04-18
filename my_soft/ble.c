#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <bluetooth/bluetooth.h>
#include <bluetooth/rfcomm.h>
#include <string.h>
#include "cJSON.h"
#include <unistd.h>

typedef struct{
    char device_status[10];
}Devices[3];

int main(int argc,char **argv)
{

    system("wget http://104.224.163.27:8080/BLE/servlet/QueryDevicesServlet");
    system("mv QueryDevicesServlet QueryResult.json");
    FILE *f;
    long len;
    char *content;
    cJSON *json,*json1,*json2,*json3;
    char *json_data=NULL;
    char *ble[3];
    f=fopen("./QueryResult.json","rb");
    fseek(f,0,SEEK_END);
    len=ftell(f);
    fseek(f,0,SEEK_SET);
    content=(char*)malloc(len+1);
    fread(content,1, len,f);
    fclose(f);

    json=cJSON_Parse(content);
    if(!json){
        printf("ERROR before:[%s]\n",cJSON_GetErrorPtr());
    }
    //vmlinuz.oldprintf("%s\n",json_data=cJSON_Print(json));

    json1=cJSON_GetObjectItem(json,"序号:0");
    json2=cJSON_GetObjectItem(json,"序号:1");
    json3=cJSON_GetObjectItem(json,"序号:2");
    Devices dd;
    strcpy(dd[0].device_status,cJSON_GetObjectItem(json1,"device_status")->valuestring);
    strcpy(dd[1].device_status,cJSON_GetObjectItem(json2,"device_status")->valuestring);
    strcpy(dd[2].device_status,cJSON_GetObjectItem(json3,"device_status")->valuestring);
    printf("device_status:%s\n",dd[0].device_status);
    printf("device_status:%s\n",dd[1].device_status);
    printf("device_status:%s\n",dd[2].device_status);

    if(strcmp(dd[0].device_status,"1")==0)
    {
        ble[0]="a";
    }
    else if(strcmp(dd[0].device_status,"0")==0){
        ble[0]="A";
    }
    if(strcmp(dd[1].device_status,"1")==0)
    {
        ble[1]="b";
    }
    else if(strcmp(dd[1].device_status,"0")==0){
        ble[1]="B";
    }
    if(strcmp(dd[2].device_status,"1")==0)
    {
        ble[2]="c";
    }
    else if(strcmp(dd[0].device_status,"0")==0){
        ble[2]="C";
    }
    for(int j=0;j<3;j++)
    {
        printf("%s\n",ble[j]);
    }

    struct sockaddr_rc addr = {0};
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
        status=write(s,ble[0],1);
        sleep(2);
        status=write(s,ble[1],1);
        sleep(2);
        status=write(s,ble[2],1);


        //printf("If you want to exit,please input : goodbye\n");
        //printf("Please input:)\n");
        /*do
        {
            scanf("%s",buf);
            status=write(s,buf,strlen(buf));
            if(status<0) perror("uh oh");
            printf("Please input:)\n");
        }while(strcmp(buf,"goodbye")!=0);*/
       /* for(int j=0;j<3;j++)
        {
            status=write(s,ble[j],1);
            if(status<0) perror("uh oh");
        }*/

        //vmlinuz.oldprintf("You have exit!\n");
    }
    else
    {
        printf("Connect Failed!\n");
    }
    free(buf);
    free(json_data);
    cJSON_Delete(json);
    close(s);
    return 0;
}
