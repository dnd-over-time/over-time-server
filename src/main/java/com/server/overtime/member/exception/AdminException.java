package com.server.overtime.member.exception;


import com.server.overtime.common.BusinessException;
import com.server.overtime.common.ErrorCode;

public class AdminException extends BusinessException {
  public AdminException(ErrorCode errorCode) {
    super(errorCode);
  }

  public static class ADMINKEY_UNAUTHORIZED extends AdminException {
    public ADMINKEY_UNAUTHORIZED() {
      super(AdminErrorCode.ADMINKEY_UNAUTHORIZED);
    }
  }
}
