package bitcamp.util;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.multipart.HttpData;
import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.http.server.HttpServerFormDecoderProvider.Builder;
import reactor.netty.http.server.HttpServerRequest;

public class HttpServletRequest {

  HttpServerRequest original;
  Map<String,Object> attrMap = new HashMap<>();
  Map<String,List<String>> paramMap = new HashMap<>();
  HttpSession session;
  String servletPath;

  public HttpServletRequest(HttpServerRequest original) {
    this.original = original;
    QueryStringDecoder qsDecoder = new QueryStringDecoder(original.uri());
    this.servletPath = qsDecoder.path();
    for (Entry<String,List<String>> entry : qsDecoder.parameters().entrySet()) {
      paramMap.put(entry.getKey(), entry.getValue());
    }
  }

  public void parseFormBody(String body) {
    // POST 요청으로 넘어온 파라미터 값을 기존 파라미터 맵에 보관한다.
    QueryStringDecoder decoder = new QueryStringDecoder("/dumy?" + body, Charset.forName("UTF-8"));
    Map<String,List<String>> bodyParams = decoder.parameters();
    for (Entry<String,List<String>> entry : bodyParams.entrySet()) {
      List<String> values = this.paramMap.get(entry.getKey());
      if (values == null) {
        values = new ArrayList<>();
        values.addAll(entry.getValue());
        this.paramMap.put(entry.getKey(), values);
      } else {
        values.addAll(entry.getValue());
      }
    }
  }

  public void setSession(HttpSession session) {
    this.session = session;
  }

  public HttpSession getSession() {
    return this.session;
  }

  public void setAttribute(String name, Object value) {
    this.attrMap.put(name, value);
  }

  public Object getAttribute(String name) {
    return this.attrMap.get(name);
  }

  public String getServletPath() {
    return this.servletPath;
  }

  public String getParameter(String name) {
    List<String> values = this.paramMap.get(name);
    if (values == null || values.size() == 0) {
      return null;
    }
    return values.get(0);
  }

  public String[] getParameterValues(String name) {
    List<String> values = this.paramMap.get(name);
    if (values == null || values.size() == 0) {
      return null;
    }
    return values.toArray(new String[0]);
  }

  public ByteBufFlux receive() {
    return original.receive();
  }

  public Map<CharSequence, List<Cookie>> allCookies() {
    return original.allCookies();
  }

  public Map<CharSequence, Set<Cookie>> cookies() {
    return original.cookies();
  }

  public Flux<?> receiveObject() {
    return original.receiveObject();
  }

  public String fullPath() {
    return original.fullPath();
  }

  public SocketAddress connectionHostAddress() {
    return original.connectionHostAddress();
  }

  public String requestId() {
    return original.requestId();
  }

  public HttpServerRequest withConnection(Consumer<? super Connection> withConnection) {
    return original.withConnection(withConnection);
  }

  public String param(CharSequence key) {
    return original.param(key);
  }

  public SocketAddress connectionRemoteAddress() {
    return original.connectionRemoteAddress();
  }

  public Map<String, String> params() {
    return original.params();
  }

  public boolean isKeepAlive() {
    return original.isKeepAlive();
  }

  public boolean isWebsocket() {
    return original.isWebsocket();
  }

  public HttpServerRequest paramsResolver(
      Function<? super String, Map<String, String>> paramsResolver) {
    return original.paramsResolver(paramsResolver);
  }

  public String scheme() {
    return original.scheme();
  }

  public HttpMethod method() {
    return original.method();
  }

  public String path() {
    return original.path();
  }

  public String connectionScheme() {
    return original.connectionScheme();
  }

  public Flux<HttpContent> receiveContent() {
    return original.receiveContent();
  }

  public String hostName() {
    return original.hostName();
  }

  public boolean isFormUrlencoded() {
    return original.isFormUrlencoded();
  }

  public int hostPort() {
    return original.hostPort();
  }

  public String uri() {
    return original.uri();
  }

  public boolean isMultipart() {
    return original.isMultipart();
  }

  public HttpVersion version() {
    return original.version();
  }

  public Flux<HttpData> receiveForm() {
    return original.receiveForm();
  }

  public Flux<HttpData> receiveForm(Consumer<Builder> formDecoderBuilder) {
    return original.receiveForm(formDecoderBuilder);
  }

  public InetSocketAddress hostAddress() {
    return original.hostAddress();
  }

  public InetSocketAddress remoteAddress() {
    return original.remoteAddress();
  }

  public HttpHeaders requestHeaders() {
    return original.requestHeaders();
  }

  public String protocol() {
    return original.protocol();
  }

  public ZonedDateTime timestamp() {
    return original.timestamp();
  }


}
