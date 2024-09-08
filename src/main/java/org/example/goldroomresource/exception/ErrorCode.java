package org.example.goldroomresource.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 공통
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    // 클라이언트의 입력 값에 대한 일반적인 오류 (@PathVariable, @RequestParam가 잘못되었을 때)
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "클라이언트의 입력 값을 확인해주세요."),
    // @RequestBody의 입력 값이 유효하지 않을 때
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "파라미터 값을 확인해주세요."),
    // 요청 본문 형식 오류 추가
    INVALID_REQUEST_BODY(HttpStatus.BAD_REQUEST, "요청 본문의 형식이 올바르지 않습니다."),
    ENTITY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 엔티티입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서비스 이용에 장애가 있습니다."),

    // 주문 생성
    INVALID_ORDER_REQUEST_BODY(HttpStatus.BAD_REQUEST, "주문 정보가 없습니다."),
    INVALID_ORDER_ITEM_REQUEST_BODY(HttpStatus.BAD_REQUEST, "주문 상품 정보가 없습니다."),
    INVALID_PARAMETER_GOLD_TYPE(HttpStatus.BAD_REQUEST, "유효한 금 상품이 선택되지 않았습니다."),
    INVALID_PARAMETER_ITEM_PRICE(HttpStatus.BAD_REQUEST, "상품 가격이 올바르지 않습니다."),
    INVALID_PARAMETER_ITEM_QUANTITY(HttpStatus.BAD_REQUEST, "상품 수량이 올바르지 않습니다."),
    INVALID_PARAMETER_SELLER_NAME(HttpStatus.BAD_REQUEST, "판매자 이름이 비어있습니다."),
    INVALID_PARAMETER_GOLD_ROOM_NAME(HttpStatus.BAD_REQUEST, "판매자의 금은방이름이 비어있습니다."),
    INVALID_PARAMETER_BUYER_NAME(HttpStatus.BAD_REQUEST, "구매자의 이름이 비어있습니다."),
    INVALID_PARAMETER_ZIP_CODE(HttpStatus.BAD_REQUEST,"우편번호가 비어있습니다."),
    INVALID_PARAMETER_ADDRESS(HttpStatus.BAD_REQUEST,"배송지 주소가 비어있습니다."),
    INVALID_PARAMETER_DETAIL_ADDRESS(HttpStatus.BAD_REQUEST,"배송지 상세 주소가 비어있습니다.");


    //공통
    private final HttpStatus status;
    private final String message;
}
