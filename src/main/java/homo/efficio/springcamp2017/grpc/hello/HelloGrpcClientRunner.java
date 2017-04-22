package homo.efficio.springcamp2017.grpc.hello;

import java.util.Arrays;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-12
 */
public class HelloGrpcClientRunner {

    public static void main(String[] args) throws InterruptedException {

        String host = "localhost";
        int port = 54321;

        HelloGrpcClientStubFactory clientStubFactory =
                new HelloGrpcClientStubFactory(host, port);

        HelloGrpcClient grpcClient =
                new HelloGrpcClient(clientStubFactory.getBlockingStub(),
                                    clientStubFactory.getAsyncStub(),
                                    clientStubFactory.getFutureStub());

        // Blocking Unary
//        grpcClient.sendBlockingUnaryMessage("Blocking Unary, gㅏ벼운 RPC, gPRC");
//        clientStubFactory.shutdownChannel();

        // Async Unary
//        grpcClient.sendAsyncUnaryMessage("Async Unary, gㅏ벼운 RPC, gRPC");
//        Thread.sleep(3000);
//        clientStubFactory.shutdownChannel();

        // Blocking Server Streaming
//        grpcClient.sendBlockingServerStreamingMessage("Blocking Server Streaming, gㅏ벼운 RPC, gPRC");
//        clientStubFactory.shutdownChannel();

        // Async Server Streaming
//        grpcClient.sendAsyncServerStreamingMessage("Async Server Streaming, gㅏ벼운 RPC, gRPC");
//        Thread.sleep(3000);
//        clientStubFactory.shutdownChannel();

        // Async Client Streaming
//        grpcClient.sendAsyncClientStreamingMessage(Arrays.asList("Async Client Streaming,", "gㅏ벼운 RPC,", "gRPC"));
//        Thread.sleep(3000);
//        clientStubFactory.shutdownChannel();
        // 아래와 같이 1초만 대기해서 서버 응답 전에 channel을 닫으면
        // 클라이언트는 응답을 못 받으며, 서버 쪽에서도 에러가 발생하지 않는다.
//        Thread.sleep(1000);
//        clientStubFactory.shutdownChannel();

        // Bidirectional Client Streaming
        grpcClient.sendBidirectionalStreamingMessage(Arrays.asList("Async Bidirectional Streaming,", "gㅏ벼운 RPC,", "gRPC"));
        Thread.sleep(3000);
        clientStubFactory.shutdownChannel();
    }
}
