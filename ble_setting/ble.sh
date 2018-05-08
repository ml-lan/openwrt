cp -r 20:16:06:15:06:15 /var/lib/bluetooth/00:1A:7D:DA:71:13
hciconfig hci0 up
hciconfig hci0 lm master

mknod /dev/rfcomm0 c 216 1

chmod 666 /dev/rfcomm0 

rfcomm bind /dev/rfcomm0 20:16:06:15:06:15

/etc/init.d/bluetoothd stop
/etc/init.d/bluetoothd start

