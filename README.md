<div align="center">

# Gold-Room-Auth

### ✨ Backend TechStack ✨
![Java](https://img.shields.io/badge/-Java-FF7800?style=flat&logo=Java&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-flat&logo=spring&logoColor=white)
![SpringBoot](https://img.shields.io/badge/-SpringBoot-6DB33F?style=flat&logo=SpringBoot&logoColor=white)
![SpringDataJPA](https://img.shields.io/badge/SpringDataJpa-236DB33F?style=flat&logo=spring&logoColor=white)
![SpringSecurity](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=Spring%20Security&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=flat&logo=MariaDB&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white)


### 🍀 서비스 개요 🍀
본 서비스는 gRPC 활용하여, 금 주문을 진행합니다. </br>
주문하기, 주문상태 변경하기, 주문 조회 서비스를 제공합니다.
</div>

</br>


## [목차]
- [Quick Start](#Quick-Start) 
- [📁 디렉토리 구조](#디렉토리-구조)
- [📦 ERD](#erd)
- [💌 API 명세서](#api-명세서)
- [✉ Git Commit Message Convention](#-git-commit-message-convention)
- [🌿 Git Branch 전략](#-git-branch-전략)

## Quick Start

## 디렉토리 구조
<details>
<summary><strong>구조도</strong></summary>
<div markdown="1">
  
```
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── example
    │   │           └── goldroomresource
    │   │               ├── GoldRoomResourceApplication.java
    │   │               ├── config
    │   │               │   ├── GrpcClientConfig.java
    │   │               │   ├── JwtAuthenticationFilter.java
    │   │               │   ├── SecurityConfig.java
    │   │               │   └── SwaggerConfig.java
    │   │               ├── exception
    │   │               │   ├── BadRequestException.java
    │   │               │   ├── BaseException.java
    │   │               │   ├── ConflictException.java
    │   │               │   ├── ErrorCode.java
    │   │               │   ├── ErrorResponse.java
    │   │               │   ├── ForbiddenException.java
    │   │               │   ├── InternalServerException.java
    │   │               │   ├── NotFoundException.java
    │   │               │   └── handler
    │   │               │       └── GlobalExceptionHandler.java
    │   │               ├── grpc
    │   │               │   └── AuthInterceptor.java
    │   │               └── order
    │   │                   ├── controller
    │   │                   │   └── OrderController.java
    │   │                   ├── domain
    │   │                   │   ├── GoldType.java
    │   │                   │   ├── Order.java
    │   │                   │   ├── OrderItem.java
    │   │                   │   └── OrderStatus.java
    │   │                   ├── dto
    │   │                   │   ├── OrderDto.java
    │   │                   │   ├── OrderItemDto.java
    │   │                   │   ├── OrderResponseDto.java
    │   │                   │   ├── UpdateOrderStatusDto.java
    │   │                   │   └── UpdateOrderStatusResponseDto.java
    │   │                   ├── repository
    │   │                   │   └── OrderRepository.java
    │   │                   └── service
    │   │                       ├── GenerateOrderNumber.java
    │   │                       ├── OrderService.java
    │   │                       ├── OrderStatusPatch.java
    │   │                       └── ValidateOrder.java
    │   ├── proto
    │   │   └── auth.proto
    │   └── resources
    │       ├── application-secret.yml
    │       └── application.yml
    └── test
        └── java
            └── org
                └── example
                    └── goldroomresource
                        └── GoldRoomResourceApplicationTests.java

```

</details>

## ERD
<img width="481" alt="주문하기" src="https://github.com/user-attachments/assets/f9dd5ba2-7578-4c75-9d45-42979ba70d0d" width="90%" height="100%">

</br>

## API 명세서

<details>
<summary><strong>주문하기</strong></summary>
<div markdown="1">

### 🙋‍♀️ 주문하기
#### 1. 설명
- `구매자`가 `주문상품`을 선택한다.
- `주문상품리스트`를 받아와 주문을 생성한다.
- `판매자` 정보는 판매글에서 받아오는 것으로 가정합니다.
</br>

#### 2. 입력
- url
> `POST` http://localhost:9999/api/orders/create

- body
```
{
  "sellerUserName": "판매자 이름",
  "goldRoomName": "판매자 금은방 이름",
  "buyerUserName": "구매자 이름",
  "orderItems": [
    {
      "goldType": "상품 이름",
      "orderItemQuantity": "상품 수량",
      "orderItemPrice": "상품 가격"
    },
    {
      "goldType": "상품 이름",
      "orderItemQuantity": "상품 수량",
      "orderItemPrice": "상품 가격"
    }
  ],
  "orderZipCode": "우편번호",
  "orderAddress": "주소",
  "orderDetailAddress": "상세주소"
}
```

#### 3. 출력
  
<details>
<summary>Response : 성공 시</summary>
<div markdown="1">
  
```
  "message": "주문이 성공적으로 접수됐습니다.",
  "order": {
    "orderId": 1,
    "orderNumber": "주문날짜시간-금은방이름-랜덤숫자6자리",
    "orderDate": "주문날자",
    "buyerUserName": "구매자 이름",
    "sellerUserName": "판매자 이름",
    "goldRoomName": "판매자 금은방 이름",
    "buyerStatus": "ORDER_COMPLETED",
    "sellerStatus": "ORDER_RECEIVED",
    "orderItems": [
      {
        "orderItemId": "주문상품_일련번호",
        "goldType": "상품 이름",
        "orderItemQuantity": "상품 수량",
        "orderItemPrice": "상품 가격"
      },
      {
        "orderItemId": "주문상품_일련번호",
        "goldType": "상품 이름",
        "orderItemQuantity": "상품 수량",
        "orderItemPrice": "상품 가격"
      }
    ],
    "totalOrderQuantity": "총 상품 수량",
    "totalOrderAmount": "총 상품 가격",
    "orderZipCode": "우편번호",
    "orderAddress": "주소",
    "orderDetailAddress": "상세주소"
  }
}
```
</details>

<details>
<summary>Response : 실패 시</summary>
<div markdown="1">
  
```
  {
    "status": 400,
    "message": "상품 가격이 올바르지 않습니다."
}

```
```
{
    "status": 400,
    "message": "주문 상품 정보가 없습니다."
}

```

</details>
</br>
</details>

<details>
<summary><strong>주문상태 변경하기</strong></summary>
<div markdown="1">

### 🔍 주문상태 변경하기
#### 1. 설명
- 주문번호의 판매자 또는 구매자는 본인의 userName을 바탕으로 주문 상태를 변경할 수 있습니다.
- 이후 모든 API 요청 Header에 JWT가 항시 포함되며, JWT 유효성을 검증합니다.
  
<details>
<summary>판매자일 때 상태 변경 목록</summary>
<div markdown="1">
  
```
  SELL_ORDER_COMPLETED("주문 완료"),  // 주문완료 시 판매자 기본 상태
  DEPOSIT_COMPLETED("입금 완료"),
  SHIPMENT_COMPLETED("발송 완료")
```
</details>

  
<details>
<summary>구매자일 때 상태 변경 목록</summary>
<div markdown="1">
  
```
    BUY_ORDER_COMPLETED("주문 완료"), // 주문완료 시 구매자 기본 상태
    TRANSFER_COMPLETED("송금 완료"),
    RECEIPT_COMPLETED("수령 완료")
```
</details>
  
#### 2. 입력
- url
> `PATCH` http://localhost:9999/api/orders/{orderNumber}/patch-status

- Parameters (path)
  - orderNumber
- body
```
{
  "userName": "사용자 이름",
  "newStatus": "새로운 주문 상태"
}
```

#### 3. 출력
- **Response : 성공 시**
```
{
  "OrderNumber": "주문번호",
  "status": "변경한 주문 상태",
  "message": "주문 상태가 성공적으로 변경됐습니다."
}
```

- **Response : 실패시**
- 권한이 없을 때
```
{
  "status": 403,
  "message": "해당 주문에 대한 변경 권한이 없습니다."
}
```

- 존재하지 않는 주문일 때
```
{
  "status": 404,
  "message": "존재하지 않는 주문입니다."
}
```
- 주문상태 변경에 실패했을 때
```
{
  "status": 409,
  "message": "주문상태 변경에 실패했습니다."
}
```

</br>

</details>

## ✉ Git Commit Message Convention
<details>
<summary>커밋 유형</summary>
<div markdown="1">
</br>
  <table>
    <tr>
      <th scope="col">커밋 유형</td>
      <th scope="col">의미</td>
    </tr>
    <tr>
      <td>feat</td>
      <td>새로운 기능 추가</td>
    </tr>
    <tr>
      <td>fix</td>
      <td>버그 수정</td>
    </tr>
    <tr>
      <td>docs</td>
      <td>문서 수정</td>
    </tr>
    <tr>
      <td>style</td>
      <td>코드 formatting, 세미콜론 누락, 코드 자체의 변경이 없는 경우</td>
    </tr>
    <tr>
      <td>refactor</td>
      <td>코드 리팩토링</td>
    </tr>
    <tr>
      <td>test</td>
      <td>테스트 코드, 리팩토링 테스트 코드 추가</td>
    </tr>
    <tr>
      <td>chore</td>
      <td>패키지 매니저 수정, 그 외 기타 수정 ex) .gitignore</td>
    </tr>
  </table>
  </br>
</div>
</details>

<details>
<summary>커밋 메세지 세부 내용</summary>
<div markdown="1">
</br>
&emsp;• 글로 작성하여 내용이 잘 전달될 수 있도록 할 것 </br></br>
&emsp;• 본문에는 변경한 내용과 이유 설명 (어떻게보다는 무엇 & 왜를 설명)</br>
&emsp;<div align="center" style="width:90%; height: 140px; border: 1px solid gray; border-radius: 1em; background-color: #AEADAB; color: black; text-align: left ">
&emsp;ex ) </br>
&emsp;refactor : 로그인 기능 변경 (title)</br>
&emsp;( 공 백 ) </br>
&emsp;기존 로그인 방식에서 ~~한 문제로 ~~한 방식으로 변경하였습니다. (content)
</br>
</br>
&emsp;feat : 로그인 기능 구현
</div>
</div>
</details>

## 🌿 Git Branch 전략
<details>
<summary>브렌치 명명 규칙</summary>
<div markdown="1">
</br>

`feat/기능명`

- ex)  feat/users_apply

</div>
</details>

<details>
<summary>브랜치 작성 방법</summary>
<div markdown="1">
</br>

- 브랜치는 기능 단위로 나눈다.
- feat 브랜치는 dev 브랜치에서 파생해서 만든다.
- PR을 통해 dev 브랜치에서 기능이 완성되면 main 브랜치로 merge 한다.

</br>

|이름|텍스트|
|----|-----|
|main|제품으로 출시될 수 있는 브랜치|
|dev|다음 출시 버전을 개발하는 브랜치|
|feat|기능을 개발하는 브랜치|

</div>
</details>

</br>
