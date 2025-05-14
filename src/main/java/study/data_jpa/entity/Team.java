package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@ToString(of = {"id", "name"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
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
