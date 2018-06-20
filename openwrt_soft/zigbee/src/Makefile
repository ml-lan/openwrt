GCC_PATH=/home/mzl/openwrt-hiwooya-stable/staging_dir/toolchain-mipsel_24kec+dsp_gcc-4.8-linaro_uClibc-0.9.33.2/bin

Zigbee_cli: zigbee_cli.o dbutil.o cJSON.o daemon.o client_util.o
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(LDFLAGS) zigbee_cli.o dbutil.o cJSON.o daemon.o client_util.o -o Zigbee_cli -I ../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include/mysql -L../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql -lmysqlclient
zigbee_cli.o: zigbee_cli.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c zigbee_cli.c -I ../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include/mysql -L../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql -lmysqlclient
dbutil.o: dbutil.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c dbutil.c -I ../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include/mysql -L../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql -lmysqlclient
cJSON.o: cJSON.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c cJSON.c
daemon.o: daemon.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c daemon.c
client_util.o: client_util.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c client_util.c
clean:
	rm *.o Zigbee
