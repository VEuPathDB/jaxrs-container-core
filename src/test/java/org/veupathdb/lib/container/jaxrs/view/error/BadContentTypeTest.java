package org.veupathdb.lib.container.jaxrs.view.error;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class BadContentTypeTest
{
  @Test
  public void constructor1() {
    var foo = new BadContentTypeError();

    assertEquals(ErrorStatus.UNSUPPORTED_MEDIA.toString(),
      foo.getStatus());
  }

  @Test
  public void constructor2() {
    var foo = new BadContentTypeError("error");

    assertEquals(ErrorStatus.UNSUPPORTED_MEDIA.toString(),
      foo.getStatus());
    assertEquals("error", foo.getMessage());
  }

  @Test
  public void constructor3() {
    var foo = new BadContentTypeError(new Exception("foo"));

    assertEquals(ErrorStatus.UNSUPPORTED_MEDIA.toString(),
      foo.getStatus());
    assertEquals("foo", foo.getMessage());
  }
}
