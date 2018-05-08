include	$(TOPDIR)/rules.mk

PKG_NAME:=Webcam
PKG_RELEASE:=1

PKG_BUILD_DIR	:=	$(BUILD_DIR)/$(PKG_NAME)

include	$(INCLUDE_DIR)/package.mk

define Package/Webcam
	SECTION:=utils
	CATEGORY:=Utilities
	TITLE:=Webcam -- web camera
	DEPENDS:=+libmysqlclient
endef

define Package/Webcam/description
	web camera
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)
	$(CP) ./src/* $(PKG_BUILD_DIR)/
endef

define Package/Webcam/install
	$(INSTALL_DIR) $(1)/bin
	$(CP) /lib64/libc.so.6 	$(1)/bin
	$(CP) /home/mzl/openwrt-hiwooya-stable/staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql/libmysqlclient.so.16 $(1)/bin
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/Webcam $(1)/bin/
endef

$(eval	$(call BuildPackage,Webcam))
