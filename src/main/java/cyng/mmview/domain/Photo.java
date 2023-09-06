package cyng.mmview.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Photo {
    @Id @GeneratedValue
    @Column(name = "photo_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post; //외래키

    private String path;
    private String fileName;
    private String fileSize;

}
