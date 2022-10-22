package jp.dcnet.ecs.form;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.dcnet.ecs.utils.MsgCont;
import lombok.Data;

@Data
public class LoginForm {

	@NotEmpty(message = "医者IDを入力してください")
	@Size(max = 5, message = "医者IDは5桁以内で入力してください。")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message="半角数字を入力ください。")
	private String ishiId;

	@NotBlank
	private String password;

	private String idAndPassword;

	/**
	 * 
	 * @return
	 */
	@AssertTrue(message = MsgCont.ISHI_PASS)
	public boolean isIdAndPasswordValid() {

		if (ishiId.isEmpty() && password.isEmpty()) {
			return false;
		}

		return true;
	}

}
