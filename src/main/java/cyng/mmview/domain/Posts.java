package cyng.mmview.domain;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Posts {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private long id;
    private String head;
    private String body;
    private boolean secret;
    private long hits;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<Photo> photos = new ArrayList<>();
}
