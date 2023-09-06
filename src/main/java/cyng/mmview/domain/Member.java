package cyng.mmview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private String id;

    private String pw;
    private String name;
    private int phone;
    private int birth;
    private String gender;

    @OneToMany(mappedBy = "member")
    private List<Posts> posts = new ArrayList<>();






}
