package ru.birdflu.springcourse.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return null;
    //return new Class[0];
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    //return new Class[0];
    return new Class[]{SpringConfig.class};
  }

  @Override
  protected String[] getServletMappings() {
    //return new String[0];
    return new String[]{"/"};
  }
}
