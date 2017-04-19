package homo.efficio.springcamp2017.grpc.hello;

import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

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

    public void sendAsyncUnaryMessage(String clientName) {

        // 클라이언트 비즈니스 로직 수행 결과인 clientName으로 request 생성
        HelloRequest request = HelloRequest.newBuilder().setClientName(clientName).build();
        logger.info("Unary Hello 서비스 Async 호출, 메시지 [" + clientName + "]");

        // 서버에 보낼 데이터를 담은 request와
        // 비동기 방식으로 서버에서 호출될 콜백 객체도 함께 파라미터로 전달
        asyncStub.unaryHello(
                request,
                // 서버에 보낼 콜백 객체
                new StreamObserver<HelloResponse>() {
                    @Override
                    public void onNext(HelloResponse response) {
                        logger.info("Async Unary 서버로부터의 응답 " + response.getWelcomeMessage());
                    }

                    @Override
                    public void onError(Throwable t) {
                        logger.log(Level.SEVERE, "Async Unary responseObserver.onError() 호출됨");
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("Async Unary 서버 응답 completed");
                    }
                }
        );

        // 서버에서 응답이 올 때까지 기다리지 않고, 호출 결과에 상관없이 다른 작업 수행 가능
        logger.info("(Nonblocking이면서)Async이니까 원격 메서드 호출 직후 바로 로그가 찍힌다.");
    }
}
