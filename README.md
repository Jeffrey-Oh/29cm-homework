# [29CM 백엔드과제 오태현] 과제제출

### 환경설정

실행 시에 application.yml Profile 값을 다음 경우에 맞게 세팅해주세요.
 - 프로그램 실행 테스트
   - real
 - 테스트 코드 실행
   - test

---

### 프로젝트 구조

유저가 서비스를 통해 상품을 선택하여 주문하고 주문 정보를 통해 결제를 처리하고 사용자에게 주문 내역을 전달한다.

---

### Items 도메인

- 시스템에 등록된 상품은 유저로부터 주문 받아 판매된다.
- 상품은 상품번호, 상품명, 판매가격, 재고수량, 판매상태의 정보로 구성된다.

### Entity 구현
요구사항에 맞게 필수 정보 및 메서드를 정의
 - 필수 정보
   - itemToken
   - itemNo
   - itemName
   - price
   - stock
 - 메서드
   - 판매 후 재고 수량 수정
   - 판매종료로 상태 변경

### Service 와 Implements 구현
- ItemsService 인터페이스에서 다음을 제공한다.
  - 상품 단일 조회
  - 상품 전체 조회
  - 판매 가능한 재고가 있는지 수량 전체 확인
  - 판매 후 재고 수량 변경
- 상품 단일 조회하는 경우
  - itemToken 값으로 Items 를 조회하고 ItemsInfo.ItemsInfoAll 객체로 변환하서 리턴한다.
- 상품 전체 조회하는 경우
  - 전체 데이터를 조회하고 ItemsInfoMapper 을 통해 List<ItemsInfo.ItemsInfoAll> 객체로 매핑하여 리턴한다.

### Facade 구현
- ItemsFacade 에서 Items 도메인을 처리하기위한 도메인 서비스만 호출한다.

### Controller 구현
- itemToken 을 전달받아 상품을 조회 하거나 값의 요청 없이 전체 목록을 조회한다.

---

### Order 도메인

- 이미 등록된 상품은 유저가 주문할 수 있다.
- 주문은 주문 등록 및 결제의 단계로 실행된다.
- 주문의 종료 명령어를 통해 결제 단계는 즉시 실행된다.

### Entity 구현
요구사항에 맞게 필수 정보 및 메서드를 정의
- 필수 정보
    - orderToken
    - 주문 상품의 item 정보

### Service 와 Implements 구현
- 주문 등록
- 주문 상품의 item 정보 조회
- 주문 결제 

- 주문 등록의 경우 
  - Order 를 생성하고 OrderItem 을 생성한다.

- 주문 상품의 item 정보 조회
  - orderToken 값으로 정보를 조회한다.

- 주문 결제의 경우
  - orderToken 을 전달하여 결제 서비스를 통해 주문 내역과 주문 금액, 지불 금액 정보를 리턴한다.

### Facade 구현
- OrderFacade 에서 Order, OrderItems 도메인을 처리하기위한 도메인 서비스와 상품의 재고 수량의 문제 여부 및 정보 업데이트를 위해 item 도메인 서비스를 호출한다.
- 결제 시에는 동기처리를 위해 synchronized 를 선언한다.
- Lazy 처리를 위해 Transaction Annotation 을 선언한다.

### Controller 구현
- Order 처리를 위해 Request 및 Response dto 를 OrderItemsInfo 에 정의하여 진행한다.