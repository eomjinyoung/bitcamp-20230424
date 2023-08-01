package bitcamp.myapp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    // 클라이언트가 요청을 보내면 이 메서드가 호출된다.
    System.out.println("ServerHandler.channelRead() 호출됨!");

    // 클라이언트로부터 받은 요청 데이터를 메모리에서 해제한다.
    ((ByteBuf) msg).release();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    // 클라이언트와 통신하는 중에 예외가 발생하면 이 메서드가 호출된다.
    System.out.println("ServerHandler.exceptionCaught()");

    // 예외에 대한 자세한 정보를 출력한다.
    cause.printStackTrace();
    ctx.close();
  }
}