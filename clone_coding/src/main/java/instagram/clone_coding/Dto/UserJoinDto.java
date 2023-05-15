package instagram.clone_coding.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor //기본 생성자 생성
@AllArgsConstructor //모든 필드를 파라미터로 받는 생성자 자동 생성
public class UserJoinDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickname;

    @NotBlank(message = "비밀번호는 최소 6자리 이상으로 입력해주세요")
    @Size(min = 6)
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
}
