package com.server.overtime.member.dao;

import com.server.overtime.member.dao.entity.MemberEntity;
import com.server.overtime.member.kakao.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    public Long save(KakaoUserInfo kakaoUserInfo) {
        MemberEntity memberEntity = memberJpaRepository.save(MemberEntity.of(kakaoUserInfo.getIdToken().toString(), kakaoUserInfo.kakaoAccount.profile.getNickname(), kakaoUserInfo.kakaoAccount.profile.getProfileImageUrl()));
        log.warn("profileImageUrl : ", kakaoUserInfo.getKakaoAccount().profile.getProfileImageUrl());
        log.warn("getThumbnailImageUrl : ", kakaoUserInfo.getKakaoAccount().profile.getThumbnailImageUrl());
        return memberEntity.getId();
    }

    public Optional<MemberEntity> findByIdToken(Long idToken) {
        return memberJpaRepository.findByIdToken(idToken.toString());
    }

    public Optional<MemberEntity> findByNickname(String nickname) {
        return memberJpaRepository.findByNickname(nickname);
    }

    public void deleteMemberByMemberId(Long memberId) {
        memberJpaRepository.deleteById(memberId);
    }
}
