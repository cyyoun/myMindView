package cyng.mmview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Members {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "members_id")
    private long id;

    @Column(name = "accnt_id")
    private String accntId;

    @Column(name="accnt_pw")
    private String accntPw;
    private String name;
    private String phone;
    private String birth;
    private String gender;

    @OneToMany(mappedBy = "members")
    private List<Posts> posts = new ArrayList<>();

    public Members() {
    }

    public Members(String accntId, String accntPw, String name, String phone, String birth, String gender) {
        this.accntId = accntId;
        this.accntPw = accntPw;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
    }
}
