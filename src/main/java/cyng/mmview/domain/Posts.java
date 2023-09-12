package cyng.mmview.domain;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Data
public class Posts {
    @Id @GeneratedValue
    @Column(name = "post_id")
    private long id;
    private String header;
    private String content;

    @CreationTimestamp
    private Timestamp createdDate;

/*    @UpdateTimestamp
    private Timestamp updateDate;*/
    private boolean secret;
    private long hits;
    private long likely;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_id")
    private Members members;

    @OneToMany(mappedBy = "post")
    private List<Photo> photos = new ArrayList<>();
}
