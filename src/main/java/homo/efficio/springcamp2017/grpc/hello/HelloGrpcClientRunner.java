package homo.efficio.springcamp2017.grpc.hello;

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
        grpcClient.sendBlockingServerStreamingMessage("Blocking Server Streaming, gㅏ벼운 RPC, gPRC");
        clientStubFactory.shutdownChannel();
    }
}
