package com.server.overtime.member.ctrl.req;

import jakarta.validation.constraints.NotNull;

public record AllInOneReq(
        @NotNull(message = "idCode은 필수입니다.")
        String idCode
) {
}