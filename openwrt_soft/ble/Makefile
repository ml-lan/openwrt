include	$(TOPDIR)/rules.mk

PKG_NAME:=Ble
PKG_RELEASE:=1

PKG_BUILD_DIR	:=	$(BUILD_DIR)/$(PKG_NAME)

include	$(INCLUDE_DIR)/package.mk

define Package/Ble
	SECTION:=utils
	CATEGORY:=Utilities
	TITLE:=Ble -- ble scan
endef

define Package/Ble/description
	ble scan test
endef

define Build/Prepare
	mkdir -p $(PKG_BUILD_DIR)
	$(CP) ./src/* $(PKG_BUILD_DIR)/
endef

define Package/Ble/install
	$(INSTALL_DIR) $(1)/bin
	$(CP) /lib64/libbluetooth.so.3 	$(1)/bin
	$(CP) /lib64/libc.so.6 	$(1)/bin
	$(INSTALL_BIN) $(PKG_BUILD_DIR)/Ble $(1)/bin/
endef

$(eval	$(call BuildPackage,Ble))
