package org.veupathdb.lib.container.jaxrs.server.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that flags a resource as allowing authenticating requests via an
 * admin token value rather than the standard authentication declared by using
 * the annotation {@link Authenticated}.
 * <p>
 * This method is meant to be used in combination with the {@link Authenticated}
 * annotation, and does nothing when used on its own.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowAdminAuth {
  /**
   * Whether admin authentication is <b>REQUIRED</b> for the endpoint.  If set
   * to true, the only authentication that will be accepted for the endpoint is
   * admin auth.  Normal user auth will be rejected even if valid.
   */
  boolean required() default false;
}
