package org.example.goldroomresource.order.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Component
public class GenerateOrderNumber {
    public String generateOrderNumber(String goldRoomName) {
        // 1. 주문날짜 형식 변환
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);

        // 2. 금은방 이름 공백 제거하기
        String sanitizedGoldRoomName = goldRoomName.replaceAll("\\s+","");

        // 3. 6자리 랜덤 숫자
        Random random = new Random();
        int randomSixNumber = 100000 + random.nextInt(900000); // 100000 ~ 999999

        // 4. 주문번호 생성 (주문날짜 + 금은방이름 + 랜덤숫자6자리)
        return formattedDate + "-" + sanitizedGoldRoomName + "-" + randomSixNumber;
    }
}
