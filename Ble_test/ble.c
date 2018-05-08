#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/param.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <bluetooth/bluetooth.h>
#include <bluetooth/rfcomm.h>
#include <string.h>
#include "cJSON.h"
#include <unistd.h>
#include <fcntl.h>

typedef struct{
    char device_status[2];
}Devices[5];

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

int main(int argc,char **argv)
{
    if((access("/var/lib/bluetooth/00:1A:7D:DA:71:13/20:16:06:15:06:15/info",F_OK))!=-1)
    {
        printf("exited\n");
    }
    else{
        printf("not exited\n");
    }
    //system("sh test.sh");
    init_daemon();
    while(1)
    {
    system("wget http://104.224.163.27:8080/ble/servlet/QueryDevicesServlet");
    FILE *f;
    long len;
    char *content;
    cJSON *json,*json1,*json2,*json3,*json4,*json5;
    char *json_data=NULL;
    char *ble[5];
    f=fopen("./QueryDevicesServlet","rb");
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
    printf("%s\n",json_data=cJSON_Print(json));

    json1=cJSON_GetObjectItem(json,"序号:0");
    json2=cJSON_GetObjectItem(json,"序号:1");
    json3=cJSON_GetObjectItem(json,"序号:2");
    json4=cJSON_GetObjectItem(json,"序号:3");
    json5=cJSON_GetObjectItem(json,"序号:4");
    Devices dd;
    strcpy(dd[0].device_status,cJSON_GetObjectItem(json1,"device_status")->valuestring);
    strcpy(dd[1].device_status,cJSON_GetObjectItem(json2,"device_status")->valuestring);
    strcpy(dd[2].device_status,cJSON_GetObjectItem(json3,"device_status")->valuestring);
    strcpy(dd[3].device_status,cJSON_GetObjectItem(json4,"device_status")->valuestring);
    strcpy(dd[4].device_status,cJSON_GetObjectItem(json5,"device_status")->valuestring);
    //printf("device_status:%s\n",dd[0].device_status);

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
    else if(strcmp(dd[2].device_status,"0")==0){
        ble[2]="C";
    }
    if(strcmp(dd[3].device_status,"1")==0)
    {
        ble[3]="d";
    }
    else if(strcmp(dd[3].device_status,"0")==0){
        ble[3]="D";
    }
    if(strcmp(dd[4].device_status,"1")==0)
    {
        ble[4]="e";
    }
    else if(strcmp(dd[4].device_status,"0")==0){
        ble[4]="E";
    }

    struct sockaddr_rc addr = {0};
    int s,status;
    char *dest,*buf;
    int i=0;
    printf("Creat socket!\n");
    dest="20:16:06:15:06:15";
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
        for(i=0;i<5;i++)
        {
            status = write(s,ble[i],1);
            sleep(1);
        }
    }
    else
    {
        printf("Connect Failed!\n");
    }
    free(buf);
    free(json_data);
    cJSON_Delete(json);
    close(s);
    system("rm -f QueryDevicesServlet");
    sleep(2);
    }
    return 0;
}
