include	$(TOPDIR)/rules.mk

PKG_NAME:=Zigbee_cli
PKG_RELEASE:=1

PKG_BUILD_DIR	:=	$(BUILD_DIR)/$(PKG_NAME)

include	$(INCLUDE_DIR)/package.mk

define Package/Zigbee_cli
	SECTION:=utils
	CATEGORY:=Utilities
	TITLE:=Zigbee_cli -- zigbee client
	DEPENDS:=+libmysqlclient
endef

define Package/Zigbee_cli/description
	zigbee client
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)
	$(CP) ./src/* $(PKG_BUILD_DIR)/
endef

define Package/Zigbee_cli/install
	$(INSTALL_DIR) $(1)/bin
	$(CP) /lib64/libc.so.6 	$(1)/bin
	$(CP) /home/mzl/openwrt-hiwooya-stable/staging_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/usr/lib/mysql/libmysqlclient.so.16 $(1)/bin
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/Zigbee_cli $(1)/bin/
endef

$(eval	$(call BuildPackage,Zigbee_cli))
