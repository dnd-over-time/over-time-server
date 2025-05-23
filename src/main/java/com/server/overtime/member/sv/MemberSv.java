package com.server.overtime.member.sv;

import com.server.overtime.member.ctrl.req.AdminKey;
import com.server.overtime.member.dao.MemberRepository;
import com.server.overtime.member.dao.entity.MemberEntity;
import com.server.overtime.member.exception.AdminException;
import com.server.overtime.member.exception.KakaoException;
import com.server.overtime.member.kakao.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSv {
    @Value("${admin.key}")
    private String adminKey;

    private final MemberRepository memberRepository;

    private final KakaoSv kakaoSv;

    public Long registerMember(String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoSv.getKakaoUserInfo(accessToken);
        if(idVertifiedUser(kakaoUserInfo)) return memberRepository.findByIdToken(kakaoUserInfo.getIdToken()).get().getId();
        if(memberRepository.findByNickname(kakaoUserInfo.kakaoAccount.profile.getNickname()).isPresent()) return 0L;

        return memberRepository.save(kakaoUserInfo);
    }

    public Long login(String accessToken) {
        KakaoUserInfo kakaoUserInfo = kakaoSv.getKakaoUserInfo(accessToken);
        if(idVertifiedUser(kakaoUserInfo)) return 0L;
        MemberEntity memberEntity = memberRepository.findByIdToken(kakaoUserInfo.getIdToken()).orElseThrow(IllegalAccessError::new);
        return memberEntity.getId();
    }

    public String getAccessToken(String idCode) {
        String accessToken = kakaoSv.getKakaoAccessToken(idCode);
        log.warn("accessToken : {}", accessToken);
        return accessToken;
    }

    public void deleteMemberByMemberId(Long memberRowId, AdminKey adminKey) {
        if (this.adminKey.equals(adminKey.getAdminKey())) memberRepository.deleteMemberByMemberId(memberRowId);
        else throw new AdminException.ADMINKEY_UNAUTHORIZED();
    }

    private Boolean idVertifiedUser(KakaoUserInfo kakaoUserInfo) {
        if(kakaoUserInfo.getIdToken() == 0L) throw new KakaoException.KAKAO_MEMBER_NOT_FOUND();
        return false;
    }


    public Long allInOne(String idCode) {
        String accessToken = kakaoSv.getKakaoAccessToken(idCode);
        log.warn("accessToken 받기 완료! : {}", accessToken);

        KakaoUserInfo kakaoUserInfo = kakaoSv.getKakaoUserInfo(accessToken);
        if(idVertifiedUser(kakaoUserInfo)) return memberRepository.findByIdToken(kakaoUserInfo.getIdToken()).get().getId();
        Optional<MemberEntity> member = memberRepository.findByNickname(kakaoUserInfo.kakaoAccount.profile.getNickname());
        if(member.isPresent()) return member.get().getId();

        return memberRepository.save(kakaoUserInfo);
    }

}
