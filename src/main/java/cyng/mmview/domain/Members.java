package cyng.mmview.domain;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Data
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
    private Gender gender;

    @OneToMany(mappedBy = "members")
    private List<Posts> posts = new ArrayList<>();

    public Members() {
    }

    public Members(String accntId, String accntPw, String name, String phone, String birth, Gender gender) {
        this.accntId = accntId;
        this.accntPw = accntPw;
        this.name = name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
    }
}
