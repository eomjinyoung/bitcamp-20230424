package bitcamp.util;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class AbstractServlet implements Servlet {

  ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
  }

  @Override
  public void destroy() {
  }

  @Override
  public String getServletInfo() {
    return "";
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.config;
  }
}
