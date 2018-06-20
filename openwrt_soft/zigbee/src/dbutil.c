#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <mysql.h>
#include <time.h>
#include <signal.h>
#include <unistd.h>
#include <sys/param.h>
#include <sys/types.h>
#include <sys/stat.h>

#define DEBUG        __FILE__,__LINE__


static MYSQL s_my_connection;
static int s_iDbconnected = 0; /*数据库连接标志 连接时为1,断开时为0*/

/*程序调试函数*/
void debug(char *pFileName,int iLine,const char *fmt, ...)
{
	char szTemp[256];
	va_list vap;

	/*memset(szTemp,0,sizeof(szTemp));*/
	va_start(vap,fmt);
	fprintf(stderr,"[%s][%d]:",pFileName,iLine);
	memset(szTemp,0,sizeof(szTemp));
	vsprintf(szTemp,fmt,vap);
	va_end(vap);
	fprintf(stderr,"%s\n",szTemp);
	fflush(stderr);
}

/*测试MySQL客户端版本*/
void mysql_version( void )
{
    printf("MySQL client version : %s ! \n",mysql_get_client_info());
}

/*登陆MySQL*/
int mysql_login(char* pServer,char *pUser,char *pPassword,char *pDataBase)
{
	MYSQL *conn_ptr = NULL;
	int iRetCode = -1;
	unsigned int uiTimeOut = 7;

	if( s_iDbconnected )
		return 0;

	conn_ptr = mysql_init(&s_my_connection);
	if( !conn_ptr )
	{
		fprintf(stderr,"mysql_init failed ! \n");
		return EXIT_FAILURE;
	}

	iRetCode = mysql_options(&s_my_connection,MYSQL_OPT_CONNECT_TIMEOUT,(const char *)&uiTimeOut);
	if( iRetCode  )
	{
		fprintf(stderr,"MySQL Connection is timeout! \n");
		return EXIT_FAILURE;
	}

	conn_ptr = NULL;
	conn_ptr = mysql_real_connect(&s_my_connection,pServer,pUser,pPassword,pDataBase,0,NULL,0);
	if( conn_ptr )
	{
		printf("MySQL Connection success!\n");
		s_iDbconnected = 1;
	}
	else
	{
		fprintf(stderr,"MySQL Connection failed!\n");
		if( mysql_errno(&s_my_connection) )
		{
			fprintf(stderr,"Connection error %d: %s!\n",mysql_errno(&s_my_connection),mysql_error(&s_my_connection));
			return EXIT_FAILURE;
		}
	}

	return EXIT_SUCCESS;
}

/*退出MySQL*/
void mysql_logout( void )
{
	if( s_iDbconnected )
		mysql_close(&s_my_connection); /*关闭连接*/
	s_iDbconnected = 0 ;
}

int mysql_proc( void )
{
	return 0;
}


int mysql_store_zigbee(char *TableName,unsigned char ucIdFlag,int iId,char *terminal_id,char *temperature,char *humidity,char *all_light_status,char *zigbee_Time)
{


	char szSql[100];
    int iRetCode=-1;

	memset(szSql,0,sizeof(szSql));
	if( 1==ucIdFlag )
	{
		sprintf(szSql,"insert into %s(id,terminal_id,temperature,humidity,all_light_status,data_time) values(%d,'%s','%s','%s','%s','%s')",TableName,iId,terminal_id,temperature,humidity,all_light_status,zigbee_Time);
	}
	else
	{
		sprintf(szSql,"insert into %s(terminal_id,temperature,humidity,all_light_status,data_time) values('%s','%s','%s','%s','%s')",TableName,terminal_id,temperature,humidity,all_light_status,zigbee_Time);
	}

	iRetCode = mysql_query(&s_my_connection,szSql);
	if( iRetCode )
	{
		fprintf(stderr,"insert error ,sqlcode=[%d] : %s !\n",mysql_errno(&s_my_connection),mysql_error(&s_my_connection));
		return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;

}
