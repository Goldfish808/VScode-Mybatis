package site.metacoding.firstapp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

public class MyParseTest {

  @Test
  public void changeStringToInt_test() {
    // given
    String val = "1";

    // when 실제로 테스트
    int result = Integer.parseInt(val);

    // then 확인
    assertEquals(1, result);
  }

}
