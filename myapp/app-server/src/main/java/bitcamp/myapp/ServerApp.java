package bitcamp.myapp;

import java.net.Socket;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.util.ApplicationContext;
import bitcamp.util.DispatcherListener;
import bitcamp.util.SqlSessionFactoryProxy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerApp {

  ApplicationContext iocContainer;
  DispatcherListener facadeListener;

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;
    //    iocContainer = new ApplicationContext(AppConfig.class);
    //    facadeListener = new DispatcherListener(iocContainer);
  }

  public void close() throws Exception {

  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    // ServerSocket을 통해 클라이언트 연결을 처리하는 스레드 그룹
    EventLoopGroup bossGroup = new NioEventLoopGroup();

    // 클라이언트와 연결된 Socket의 통신을 처리하는 스레드 그룹
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      // 서버 설정을 수행할 객체 준비
      ServerBootstrap bootstrap = new ServerBootstrap();

      // 서버로서 작업을 수행할 스레드 그룹을 설정
      bootstrap.group(bossGroup, workerGroup);

      // 클라이언트와 통신하는 채널의 타입을 지정
      // 클라이언트가 연결되면 이 타입의 객체를 생성한다.
      // 이 채널 객체가 클라이언트와의 통신을 수행한다.
      bootstrap.channel(NioServerSocketChannel.class);

      // 클라이언트와 연결된 채널을 준비시키는 객체 설정
      bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          // 클라이언트가 연결되면 이 메서드가 호출되어
          // 통신 채널을 준비한다.
          // => 클라이언트가 보낸 요청을 처리할 객체 등록
          ch.pipeline().addLast(new ServerHandler());
        }
      });

      // 대기열 개수 설정
      bootstrap.option(ChannelOption.SO_BACKLOG, 128);

      // 클라이언트의 연결을 계속 유지할 것인지 설정
      bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

      System.out.println("ServerBootstrap 설정 완료!");

      // 설정된 정보를 기반으로 서버 시작
      ChannelFuture f = bootstrap.bind(port).sync();
      System.out.println("서버 시작됨!");

      // 모든 클라이언트의 연결이 끊어지면 종료하라고 예약한다.
      // => 서버가 종료할 때까지 리턴되지 않는다.
      f.channel().closeFuture().sync();
      System.out.println("서버 종료됨!");

    } finally {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  private void processRequest(Socket socket) {
    try {

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      SqlSessionFactoryProxy sqlSessionFactoryProxy =
          (SqlSessionFactoryProxy) iocContainer.getBean(SqlSessionFactory.class);
      sqlSessionFactoryProxy.clean();
    }
  }
}