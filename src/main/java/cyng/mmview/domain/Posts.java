package cyng.mmview.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Data
public class Posts {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private long id;
    private String head;
    private String body;
    private boolean secret;
    private long hits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id")
    private Members members;

    @OneToMany(mappedBy = "post")
    private List<Photo> photos = new ArrayList<>();
}
