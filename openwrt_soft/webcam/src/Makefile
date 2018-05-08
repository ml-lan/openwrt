GCC_PATH=/home/mzl/openwrt-hiwooya-stable/staging_dir/toolchain-mipsel_24kec+dsp_gcc-4.8-linaro_uClibc-0.9.33.2/bin

Webcam: webcam.o
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(LDFLAGS) webcam.o -o Webcam -I../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include/mysql -L../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql -lmysqlclient
webcam.o: webcam.c
	$(GCC_PATH)/mipsel-openwrt-linux-gcc $(CFLAGS) -c webcam.c -I../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/include/mysql -L../../../staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql -lmysqlclient
clean:
	rm *.o Webcam
