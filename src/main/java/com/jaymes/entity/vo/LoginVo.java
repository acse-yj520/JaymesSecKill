package com.jaymes.entity.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginVo {

  @NotNull
  @Pattern(regexp = "1\\d{10}", message = "手机号码不合法")
  private String mobile;

  @NotNull
  @Length(min = 32)
  private String password;
}
