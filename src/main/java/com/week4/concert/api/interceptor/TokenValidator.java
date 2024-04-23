package com.week4.concert.api.interceptor;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenValidator {

    private final OngoingSerivce ongoingSerivce;

    @Transactional
    public void valid(Long userId) {
        ongoingSerivce.check(userId);
    }
}
