package bitcamp.util;

import java.io.IOException;

public interface ActionListener {
  void service(BreadcrumbPrompt prompt) throws IOException;
}
