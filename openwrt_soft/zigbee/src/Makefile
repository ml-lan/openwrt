GCC_PATH=/home/mzl/openwrt-hiwooya-stable/staging_dir/toolchain-mipsel_24kec+dsp_gcc-4.8-linaro_uClibc-0.9.33.2/bin

Zigbee_cli: zigbee_cli.o
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(LDFLAGS) zigbee_cli.o -o Zigbee_cli 
zigbee_cli.o: zigbee_cli.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c zigbee_cli.c
clean:
	rm *.o Zigbee
