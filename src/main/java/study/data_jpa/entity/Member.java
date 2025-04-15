package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id") // 외래키 컬럼명
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public void changeTeam (Team team) {
        if(this.team != null){
            this.team.getMembers().remove(this);
        }
        this.team = team;
        if(team != null){
            team.getMembers().add(this);
        }
    }

}
