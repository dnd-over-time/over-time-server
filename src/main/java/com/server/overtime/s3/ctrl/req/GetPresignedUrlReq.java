package com.server.overtime.s3.ctrl.req;

import jakarta.validation.constraints.NotNull;

public record GetPresignedUrlReq(
    @NotNull(message = "fileName은 필수입니다. 확장자명을 포함해주세요.")
    String fileName
) {}
