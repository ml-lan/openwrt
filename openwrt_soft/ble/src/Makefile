GCC_PATH=/home/mzl/openwrt-hiwooya-stable/staging_dir/toolchain-mipsel_24kec+dsp_gcc-4.8-linaro_uClibc-0.9.33.2/bin

Ble: ble.o cJSON.o
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(LDFLAGS) ble.o cJSON.o -o Ble -L ../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include -lbluetooth
cJSON.o: cJSON.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c cJSON.c
ble.o: ble.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c ble.c -L ../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include -lbluetooth
clean:
	rm *.o Ble
