package cn.xilio.joty.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccessUserType {
    NEW_USER("新用户", "新用户"),
    OLD_USER("老用户", "老用户");
    private final String value;
    private final String desc;
}
