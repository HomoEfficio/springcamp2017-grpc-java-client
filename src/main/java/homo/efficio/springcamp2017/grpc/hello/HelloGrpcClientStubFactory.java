package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-12
 */
public class HelloGrpcClientStubFactory {

    private final Logger logger = Logger.getLogger(HelloGrpcClientStubFactory.class.getName());

    private final ManagedChannel channel;
    private final HelloSpringCampGrpc.HelloSpringCampBlockingStub blockingStub;
    private final HelloSpringCampGrpc.HelloSpringCampStub asyncStub;
    private final HelloSpringCampGrpc.HelloSpringCampFutureStub futureStub;

    public HelloGrpcClientStubFactory(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                                            .usePlaintext(true)
                                            .build();
        this.blockingStub = HelloSpringCampGrpc.newBlockingStub(channel);
        this.asyncStub = HelloSpringCampGrpc.newStub(channel);
        this.futureStub = HelloSpringCampGrpc.newFutureStub(channel);
    }

    public void shutdownChannel() throws InterruptedException {
        logger.info("gRPC Channel shutdown...");
        this.channel.shutdown().awaitTermination(2, TimeUnit.SECONDS);
    }

    public HelloSpringCampGrpc.HelloSpringCampBlockingStub getBlockingStub() {
        return blockingStub;
    }

    public HelloSpringCampGrpc.HelloSpringCampStub getAsyncStub() {
        return asyncStub;
    }

    public HelloSpringCampGrpc.HelloSpringCampFutureStub getFutureStub() {
        return futureStub;
    }
}
