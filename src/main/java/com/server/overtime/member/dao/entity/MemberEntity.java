package com.server.overtime.member.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    @Id
    @Column(name = "memberRowId", length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname", nullable = false, unique = true, length = 10)
    private String nickname;

    @Column(name = "id_token", nullable = false, length = 255)
    private String idToken;

    @Column(name = "profile_image_url", nullable = false, length = 255)
    private String profileImageUrl;

    public static MemberEntity of(String idToken, String nickname, String profileImageUrl) {
        return new MemberEntity(null, idToken, nickname, profileImageUrl);
    }
}
