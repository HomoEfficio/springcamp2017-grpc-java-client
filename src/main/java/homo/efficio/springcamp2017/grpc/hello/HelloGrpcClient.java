package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.StatusRuntimeException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-12
 */
public class HelloGrpcClient {

    private final Logger logger = Logger.getLogger(HelloGrpcClient.class.getName());

    private final HelloSpringCampGrpc.HelloSpringCampBlockingStub blockingStub;
    private final HelloSpringCampGrpc.HelloSpringCampStub asyncStub;
    private final HelloSpringCampGrpc.HelloSpringCampFutureStub futureStub;

    public HelloGrpcClient(HelloSpringCampGrpc.HelloSpringCampBlockingStub blockingStub,
                           HelloSpringCampGrpc.HelloSpringCampStub asyncStub,
                           HelloSpringCampGrpc.HelloSpringCampFutureStub futureStub) {
        this.blockingStub = blockingStub;
        this.asyncStub = asyncStub;
        this.futureStub = futureStub;
    }

    public void sendBlockingUnaryMessage(String clientName) {

        // 클라이언트 비즈니스 로직 수행 결과인 clientName으로 request 생성
        HelloRequest request = HelloRequest.newBuilder().setClientName(clientName).build();
        HelloResponse response;

        try {
            logger.info("Unary Hello 서비스 호출, 메시지 [" + clientName + "]");
            response = blockingStub.unaryHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.SEVERE, "Unary Hello 서비스 호출 중 실패: " + e.getStatus());
            return;
        }

        logger.info("Unary Hello 서비스 응답: " + response.getWelcomeMessage());
    }
}
