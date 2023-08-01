package bitcamp.myapp;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ServerHandler extends SimpleChannelInboundHandler<Object> {
49  
50      private HttpRequest request;
51      /** Buffer that stores the response content */
52      private final StringBuilder buf = new StringBuilder();
53  
54      @Override
55      public void channelReadComplete(ChannelHandlerContext ctx) {
56          ctx.flush();
57      }
58  
59      @Override
60      protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
61          if (msg instanceof HttpRequest) {
62              HttpRequest request = this.request = (HttpRequest) msg;
63  
64              if (HttpUtil.is100ContinueExpected(request)) {
65                  send100Continue(ctx);
66              }
67  
68              buf.setLength(0);
69              buf.append("WELCOME TO THE WILD WILD WEB SERVER\r\n");
70              buf.append("===================================\r\n");
71  
72              buf.append("VERSION: ").append(request.protocolVersion()).append("\r\n");
73              buf.append("HOSTNAME: ").append(request.headers().get(HttpHeaderNames.HOST, "unknown")).append("\r\n");
74              buf.append("REQUEST_URI: ").append(request.uri()).append("\r\n\r\n");
75  
76              HttpHeaders headers = request.headers();
77              if (!headers.isEmpty()) {
78                  for (Map.Entry<String, String> h: headers) {
79                      CharSequence key = h.getKey();
80                      CharSequence value = h.getValue();
81                      buf.append("HEADER: ").append(key).append(" = ").append(value).append("\r\n");
82                  }
83                  buf.append("\r\n");
84              }
85  
86              QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
87              Map<String, List<String>> params = queryStringDecoder.parameters();
88              if (!params.isEmpty()) {
89                  for (Entry<String, List<String>> p: params.entrySet()) {
90                      String key = p.getKey();
91                      List<String> vals = p.getValue();
92                      for (String val : vals) {
93                          buf.append("PARAM: ").append(key).append(" = ").append(val).append("\r\n");
94                      }
95                  }
96                  buf.append("\r\n");
97              }
98  
99              appendDecoderResult(buf, request);
100         }
101 
102         if (msg instanceof HttpContent) {
103             HttpContent httpContent = (HttpContent) msg;
104 
105             ByteBuf content = httpContent.content();
106             if (content.isReadable()) {
107                 buf.append("CONTENT: ");
108                 buf.append(content.toString(CharsetUtil.UTF_8));
109                 buf.append("\r\n");
110                 appendDecoderResult(buf, request);
111             }
112 
113             if (msg instanceof LastHttpContent) {
114                 buf.append("END OF CONTENT\r\n");
115 
116                 LastHttpContent trailer = (LastHttpContent) msg;
117                 if (!trailer.trailingHeaders().isEmpty()) {
118                     buf.append("\r\n");
119                     for (CharSequence name: trailer.trailingHeaders().names()) {
120                         for (CharSequence value: trailer.trailingHeaders().getAll(name)) {
121                             buf.append("TRAILING HEADER: ");
122                             buf.append(name).append(" = ").append(value).append("\r\n");
123                         }
124                     }
125                     buf.append("\r\n");
126                 }
127 
128                 if (!writeResponse(trailer, ctx)) {
129                     // If keep-alive is off, close the connection once the content is fully written.
130                     ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
131                 }
132             }
133         }
134     }
135 
136     private static void appendDecoderResult(StringBuilder buf, HttpObject o) {
137         DecoderResult result = o.decoderResult();
138         if (result.isSuccess()) {
139             return;
140         }
141 
142         buf.append(".. WITH DECODER FAILURE: ");
143         buf.append(result.cause());
144         buf.append("\r\n");
145     }
146 
147     private boolean writeResponse(HttpObject currentObj, ChannelHandlerContext ctx) {
148         // Decide whether to close the connection or not.
149         boolean keepAlive = HttpUtil.isKeepAlive(request);
150         // Build the response object.
151         FullHttpResponse response = new DefaultFullHttpResponse(
152                 HTTP_1_1, currentObj.decoderResult().isSuccess()? OK : BAD_REQUEST,
153                 Unpooled.copiedBuffer(buf.toString(), CharsetUtil.UTF_8));
154 
155         response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
156 
157         if (keepAlive) {
158             // Add 'Content-Length' header only for a keep-alive connection.
159             response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
160             // Add keep alive header as per:
161             // - https://www.w3.org/Protocols/HTTP/1.1/draft-ietf-http-v11-spec-01.html#Connection
162             response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
163         }
164 
165         // Encode the cookie.
166         String cookieString = request.headers().get(HttpHeaderNames.COOKIE);
167         if (cookieString != null) {
168             Set<Cookie> cookies = ServerCookieDecoder.STRICT.decode(cookieString);
169             if (!cookies.isEmpty()) {
170                 // Reset the cookies if necessary.
171                 for (Cookie cookie: cookies) {
172                     response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
173                 }
174             }
175         } else {
176             // Browser sent no cookie.  Add some.
177             response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("key1", "value1"));
178             response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode("key2", "value2"));
179         }
180 
181         // Write the response.
182         ctx.write(response);
183 
184         return keepAlive;
185     }
186 
187     private static void send100Continue(ChannelHandlerContext ctx) {
188         FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, CONTINUE, Unpooled.EMPTY_BUFFER);
189         ctx.write(response);
190     }
191 
192     @Override
193     public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
194         cause.printStackTrace();
195         ctx.close();
196     }
197 }