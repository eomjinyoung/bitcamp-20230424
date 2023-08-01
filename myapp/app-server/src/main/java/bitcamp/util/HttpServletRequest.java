package bitcamp.util;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.multipart.HttpData;
import reactor.core.publisher.Flux;
import reactor.netty.ByteBufFlux;
import reactor.netty.Connection;
import reactor.netty.http.server.HttpServerFormDecoderProvider.Builder;
import reactor.netty.http.server.HttpServerRequest;

public class HttpServletRequest {

  HttpServerRequest original;

  public HttpServletRequest(HttpServerRequest original) {
    this.original = original;
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
