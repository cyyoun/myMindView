package cyng.mmview.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class JoinMembersForm {
    @NotBlank(message = "아이디를 입력해 주세요.")
    @Length(min = 8, max = 20, message = "아이디는 8글자 이상 20글자 이하로 입력해 주세요.")
    private String accntId;

    @NotBlank(message = "비밀번호를 입력해 주세요.")
    @Length(min = 8, max = 20, message = "비밀번호는 8글자 이상 20글자 이하로 입력해 주세요.")
    private String accntPw;

    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "핸드폰 번호를 입력해 주세요.")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 핸드폰 번호를 입력해 주세요.")
    private String phone;

    @NotBlank(message = "생년월일 8글자를 입력해 주세요.")
    @Pattern(regexp = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|1[0-9]|2[0-9]|3[01])", message = "올바른 생년월일을 입력해 주세요.")
    private String birth;

    @NotNull(message = "성별을 선택해 주세요.")
    private Gender gender;




}
