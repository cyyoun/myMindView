package cyng.mmview.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Members {
    @Id @GeneratedValue
    private String mem_id;
    private String pw;
    private String name;
    private int birth;
    private int phone;
    private String gender;





}
