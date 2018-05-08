#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdarg.h>
#include <mysql.h>

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

/*向MySQL数据库中存储图片*/
int mysql_store_image(char *pFileName,char *pImageTableName,unsigned char ucIdFlag,int iId)
{

	FILE *fp;
	char szImageName[31];
	char szImageData[1024*1000];/*图片大小最大不超过1M*/
	char szStoreImageData[2*1024*1000+1];
	char szSql[2*1024*1000+1];
	unsigned long ulReadLength = 0,ulStoreLength = 0;
	int iRetCode = -1;


	fp = fopen(pFileName,"rb");

	if( NULL==fp )
	{
		fprintf(stderr,"This file: [ %s ] isn't exsit !\n",pFileName);
		return EXIT_FAILURE;
	}

	memset(szImageData,0,sizeof(szImageData));
	ulReadLength = fread(szImageData,1,1024*1000,fp);
	fclose(fp);

	if( !ulReadLength )
	{
		fprintf(stderr,"Read file found error !\n");
		return EXIT_FAILURE;
	}

	printf("ulReadLength = %ld \n",ulReadLength);

	memset(szImageName,0,sizeof(szImageName));
	memcpy(szImageName,pFileName,strlen(pFileName));
	memset(szStoreImageData,0,sizeof(szStoreImageData));
	ulStoreLength = mysql_real_escape_string(&s_my_connection,szStoreImageData,szImageData,ulReadLength);
	/*二进制数据可能包含一些特殊字符,这些字符在sql语句中可能会引起一些问题,
	所以必须转义,理论上每个字符都可能是特殊字符,所以szStoreImageData数组大小是szImageData数组大小的两倍,
	该函数还会在szStoreImageData数组最后加上结尾符
	*/
	printf("ulStoreLength = %ld \n",ulStoreLength);

	memset(szSql,0,sizeof(szSql));
	if( 1==ucIdFlag )
	{
		sprintf(szSql,"insert into %s(id,name,data) values(%d,'%s','%s')",pImageTableName,iId,szImageName,szStoreImageData);
	}
	else
	{
		sprintf(szSql,"insert into %s(name,data) values('%s','%s')",pImageTableName,szImageName,szStoreImageData);
	}

	iRetCode = mysql_query(&s_my_connection,szSql);
	if( iRetCode )
	{
		fprintf(stderr,"insert error ,sqlcode=[%d] : %s !\n",mysql_errno(&s_my_connection),mysql_error(&s_my_connection));
		return EXIT_FAILURE;
	}

	return EXIT_SUCCESS;

}

int main(int argc,char *argv[])
{
	char szServer[20] = "104.224.163.27"; /*127.0.0.1*/
	char szUser[20] = "mzl";
	char szPassword[20] = "MySqlPass123...";
	char szDatabase[20] = "ble";
	char szFileName[30],szTableName[30];
	unsigned char ucIdFlag = 0;/*手动插入ID标志*/
	int iRetCode = -1,iId =0;

  	iRetCode = mysql_login(szServer,szUser,szPassword,szDatabase);
	if( iRetCode )
	{
		return 1;
	}

	memset(szFileName,0,sizeof(szFileName));
	memset(szTableName,0,sizeof(szTableName));
	strcpy(szFileName,"2.jpg");
	strcpy(szTableName,"webcam_pic");
	ucIdFlag = 0;
	iId =1;

	iRetCode = mysql_store_image( szFileName,szTableName,ucIdFlag,iId );

	if( !iRetCode )
	{
		fprintf(stderr,"Image has been stored in the database !\n");
	}

	mysql_logout();

	return 0;
}

