package cyng.mmview.domain;

import lombok.Data;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "제목을 입력해 주세요.")
    private String header;

    @NotBlank(message = "내용을 입력해 주세요.")
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
