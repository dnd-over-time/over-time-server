package com.server.overtime.member.ctrl.res;

public record JoinRes(
        Long memberRowId,
        String nickname,
        String profileImageUrl
){}