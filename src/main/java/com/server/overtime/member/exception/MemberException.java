package com.server.overtime.member.exception;


import com.server.overtime.common.BusinessException;
import com.server.overtime.common.ErrorCode;

public class MemberException extends BusinessException {
  public MemberException(ErrorCode errorCode) {
    super(errorCode);
  }

  public static class MEMBER_NOT_FOUND extends MemberException {
    public MEMBER_NOT_FOUND() {
      super(MemberErrorCode.MEMBER_NOT_FOUND);
    }
  }

  public static class MEMBER_NICKNAME_CONFLICT extends MemberException {
    public MEMBER_NICKNAME_CONFLICT() {
      super(MemberErrorCode.MEMBER_NICKNAME_CONFLICT);
    }
  }
}
