package com.kai.football_api.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 이 어노테션을 매서드에만 붙일수 있게 해줌
@Retention(RetentionPolicy.RUNTIME) // 이 어노태션 데이터를 runtime 에서도 유지하게 해줌
public @interface PreventDuplicate {
}