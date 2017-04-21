package homo.efficio.springcamp2017.grpc.hello;

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

    public void sendAsyncUnaryMessage() {

        // 클라이언트 비즈니스 로직 수행 결과를 message에 할당인 clientName으로 request 생성
        String message = "입금해 형 ㅋ";

        // message로 request 생성
        HelloRequest request = HelloRequest.newBuilder().setRequest(message).build();

        // 서버에 보낼 데이터를 담은 request와
        // 비동기 방식으로 서버에서 호출될 콜백 객체도 함께 파라미터로 전달
        logger.info("[김정은] 요청 전송 : " + message);
        asyncStub.unaryHello(
                request,
                // 서버에 보낼 콜백 객체
                new StreamObserver<HelloResponse>() {
                    @Override
                    public void onNext(HelloResponse response) {
                        logger.info("[트럼프로부터의 응답] : " + response.getResponse());
                    }

                    @Override
                    public void onError(Throwable t) {
                        logger.log(Level.SEVERE, "Async Unary responseObserver.onError() 호출됨");
                    }

                    @Override
                    public void onCompleted() {
                        logger.info("[트럼프로부터의 응답 종료]");
                    }
                }
        );

        // 서버에서 응답이 올 때까지 기다리지 않고, 호출 결과에 상관없이 다른 작업 수행 가능
        logger.info("[김정은] : 걍 핵이나 쏴야지 ㅋㅋ");
    }
}
