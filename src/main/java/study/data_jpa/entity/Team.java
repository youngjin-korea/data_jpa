package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team") // 외래키를 가지고 있는 속성
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
