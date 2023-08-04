package bitcamp.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.cookie.Cookie;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;
import reactor.netty.http.server.HttpServerResponse;
import reactor.netty.http.server.WebsocketServerSpec;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;

public class HttpServletResponse {

  HttpServerResponse original;

  // 서블릿이 클라이언트에게 응답할 때 사용할 출력 스트림 도구
  StringWriter buf = new StringWriter();
  PrintWriter out;

  // 응답 콘텐트의 타입
  String contentType = "text/plain;charset=ISO-8859-1";

  public HttpServletResponse(HttpServerResponse original) {
    this.original = original;
  }

  public PrintWriter getWriter() {
    if (out == null) {
      out = new PrintWriter(buf);
    }
    return this.out;
  }

  public String getContent() {
    return buf.toString();
  }

  public void setContentType(String contentType) {
    if (out != null) {
      // 출력 스트림을 사용한 상태라면 콘텐트 설정을 무시한다.
      return;
    }

    this.contentType = contentType;
  }

  public String getContentType() {
    return this.contentType;
  }

  public SocketAddress hostAddress() {
    return original.hostAddress();
  }

  public Map<CharSequence, List<Cookie>> allCookies() {
    return original.allCookies();
  }

  public Map<CharSequence, Set<Cookie>> cookies() {
    return original.cookies();
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

  public HttpServerResponse addCookie(Cookie cookie) {
    return original.addCookie(cookie);
  }

  public SocketAddress remoteAddress() {
    return original.remoteAddress();
  }

  public HttpServerResponse addHeader(CharSequence name, CharSequence value) {
    return original.addHeader(name, value);
  }

  public SocketAddress connectionRemoteAddress() {
    return original.connectionRemoteAddress();
  }

  public boolean isKeepAlive() {
    return original.isKeepAlive();
  }

  public HttpServerResponse chunkedTransfer(boolean chunked) {
    return original.chunkedTransfer(chunked);
  }

  public boolean isWebsocket() {
    return original.isWebsocket();
  }

  public String scheme() {
    return original.scheme();
  }

  public HttpServerResponse withConnection(Consumer<? super Connection> withConnection) {
    return original.withConnection(withConnection);
  }

  public HttpMethod method() {
    return original.method();
  }

  public ByteBufAllocator alloc() {
    return original.alloc();
  }

  public HttpServerResponse compression(boolean compress) {
    return original.compression(compress);
  }

  public String path() {
    return original.path();
  }

  public Mono<Void> neverComplete() {
    return original.neverComplete();
  }

  public String connectionScheme() {
    return original.connectionScheme();
  }

  public String hostName() {
    return original.hostName();
  }

  public boolean hasSentHeaders() {
    return original.hasSentHeaders();
  }

  public NettyOutbound send(Publisher<? extends ByteBuf> dataStream) {
    return original.send(dataStream);
  }

  public HttpServerResponse header(CharSequence name, CharSequence value) {
    return original.header(name, value);
  }

  public int hostPort() {
    return original.hostPort();
  }

  public String uri() {
    return original.uri();
  }

  public HttpServerResponse headers(HttpHeaders headers) {
    return original.headers(headers);
  }

  public HttpVersion version() {
    return original.version();
  }

  public HttpServerResponse keepAlive(boolean keepAlive) {
    return original.keepAlive(keepAlive);
  }

  public NettyOutbound send(Publisher<? extends ByteBuf> dataStream, Predicate<ByteBuf> predicate) {
    return original.send(dataStream, predicate);
  }

  public HttpHeaders responseHeaders() {
    return original.responseHeaders();
  }

  public Mono<Void> send() {
    return original.send();
  }

  public NettyOutbound sendHeaders() {
    return original.sendHeaders();
  }

  public Mono<Void> sendNotFound() {
    return original.sendNotFound();
  }

  public NettyOutbound sendByteArray(Publisher<? extends byte[]> dataStream) {
    return original.sendByteArray(dataStream);
  }

  public Mono<Void> sendRedirect(String location) {
    return original.sendRedirect(location);
  }

  public Mono<Void> sendWebsocket(
      BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<Void>> websocketHandler) {
    return original.sendWebsocket(websocketHandler);
  }

  public NettyOutbound sendFile(Path file) {
    return original.sendFile(file);
  }

  public Mono<Void> sendWebsocket(
      BiFunction<? super WebsocketInbound, ? super WebsocketOutbound, ? extends Publisher<Void>> websocketHandler,
          WebsocketServerSpec websocketServerSpec) {
    return original.sendWebsocket(websocketHandler, websocketServerSpec);
  }

  public HttpServerResponse sse() {
    return original.sse();
  }

  public NettyOutbound sendFile(Path file, long position, long count) {
    return original.sendFile(file, position, count);
  }

  public HttpResponseStatus status() {
    return original.status();
  }

  public HttpServerResponse status(HttpResponseStatus status) {
    return original.status(status);
  }

  public HttpServerResponse status(int status) {
    return original.status(status);
  }

  public HttpServerResponse trailerHeaders(Consumer<? super HttpHeaders> trailerHeaders) {
    return original.trailerHeaders(trailerHeaders);
  }

  public NettyOutbound sendFileChunked(Path file, long position, long count) {
    return original.sendFileChunked(file, position, count);
  }

  public NettyOutbound sendGroups(Publisher<? extends Publisher<? extends ByteBuf>> dataStreams) {
    return original.sendGroups(dataStreams);
  }

  public NettyOutbound sendObject(Publisher<?> dataStream) {
    return original.sendObject(dataStream);
  }

  public NettyOutbound sendObject(Publisher<?> dataStream, Predicate<Object> predicate) {
    return original.sendObject(dataStream, predicate);
  }

  public NettyOutbound sendObject(Object message) {
    return original.sendObject(message);
  }

  public NettyOutbound sendString(Publisher<? extends String> dataStream) {
    return original.sendString(dataStream);
  }

  public NettyOutbound sendString(Publisher<? extends String> dataStream, Charset charset) {
    return original.sendString(dataStream, charset);
  }

  public <S> NettyOutbound sendUsing(Callable<? extends S> sourceInput,
      BiFunction<? super Connection, ? super S, ?> mappedInput, Consumer<? super S> sourceCleanup) {
    return original.sendUsing(sourceInput, mappedInput, sourceCleanup);
  }

  public void subscribe(Subscriber<? super Void> s) {
    original.subscribe(s);
  }

  public Mono<Void> then() {
    return original.then();
  }

  public NettyOutbound then(Publisher<Void> other) {
    return original.then(other);
  }

  public NettyOutbound then(Publisher<Void> other, Runnable onCleanup) {
    return original.then(other, onCleanup);
  }


}
