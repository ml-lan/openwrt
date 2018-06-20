#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include<string.h>
#include "cJSON.h"
#include<unistd.h>
#include<fcntl.h>

int main(int argc, char *argv[])
{


    char szServer[20] = "104.224.163.27";
    char szUser[20] = "mzl";
    char szPassword[20] = "MySqlPass123...";
    char szDatabase[20] = "ble";
    char szTableName[30];
    unsigned char ucIdFlag = 0;
    int iRetCode = -1,iId = 0;
    char terminal_id[5];
    char temperature[10];
    char humidity[10];
    char temperature_tmp[5];
    char humidity_tmp[5];
    char all_light_status[3];
    char zigbee_time[30];
    int temperature_int,humidity_int,all_light_status_int;
    unsigned char buf[6]={0x3A,0x00,0xFF,0x01,0xC4,0x23};
    int len;

    iRetCode = mysql_login(szServer,szUser,szPassword,szDatabase);
    if(iRetCode)
    {
        return 1;
    }
    while(1)
    {
        system("wget http://104.224.163.27:8080/ble/servlet/QueryZigbeeByLately?terminal_id=0001");
        sleep(5);
        FILE *f;
        long f_len;
        char *content;
        cJSON *json;
        char *json_data=NULL;
        f = fopen("./QueryZigbeeByLately?terminal_id=0001","rb");
        fseek(f,0,SEEK_END);
        f_len = ftell(f);
        fseek(f,0,SEEK_SET);
        content = (char*)malloc(f_len+1);
        fread(content,1,f_len,f);
        fclose(f);

        json= cJSON_Parse(content);
        if(!json)
        {
            printf("ERROR before:[%s]\n",cJSON_GetErrorPtr());

        }
        printf("%s\n",json_data=cJSON_Print(json));
        printf("free 1 test\n");
        free(json_data);
        printf("free json1\n");
        cJSON_Delete(json);
        printf("free json\n");
        //查询所有终端温湿度
        int client_sockfd = client_util(argv[1]);
        len = send(client_sockfd,buf,6,0);
        if((len = recv(client_sockfd,buf,22,0))>0)
        {

            printf("%x\n",buf[4]);
            printf("%x\n",buf[5]);
            temperature_int = (int)(buf[4]);
            humidity_int = (int)(buf[5]);
        }
        sprintf(temperature_tmp,"%d",temperature_int);
        sprintf(humidity_tmp,"%d",humidity_int);

        printf("%s\n",temperature_tmp);
        printf("%s\n",humidity_tmp);
        close(client_sockfd);
        printf("free socketfd\n");
        memset(temperature,0,sizeof(temperature));
        strcpy(temperature,temperature_tmp);

        memset(humidity,0,sizeof(humidity));
        strcpy(humidity,humidity_tmp);

        memset(szTableName,0,sizeof(szTableName));
        strcpy(szTableName,"zigbee");

        memset(terminal_id,0,sizeof(terminal_id));
        strcpy(terminal_id,"0001");
        memset(all_light_status,0,sizeof(all_light_status));
        strcpy(all_light_status,"1");

        ucIdFlag = 0;
        iId = 1;

        time_t t =time(0);
        strftime(zigbee_time,255,"%Y-%m-%d %T",localtime(&t));
        iRetCode = mysql_store_zigbee(szTableName,ucIdFlag,iId,terminal_id,temperature,humidity,all_light_status,zigbee_time);

        if(!iRetCode)
        {
            fprintf(stderr,"stored in the database\n");
        }

        system("rm -f QueryZigbeeByLately*");
        printf("free file\n");
        sleep(5);
    }
    mysql_logout();
    return 0;

}


