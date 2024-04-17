package com.week4.concert.UnitTest.domain.concert;

import com.week4.concert.Fixtures;
import com.week4.concert.domain.concert.Concert;
import com.week4.concert.domain.concert.ConcertReader;
import com.week4.concert.domain.concert.ConcertRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ConcertReaderTest {

    private ConcertRepository concertRepository;
    private ConcertReader concertReader;

    @BeforeEach
    void setUp() {
        concertRepository = mock(ConcertRepository.class);
        concertReader = new ConcertReader(concertRepository);
    }

    @Test
    @DisplayName("날짜와 제목으로 콘서트 정보 조회")
    void getConcertInfo() {
        //given
        Concert concert = Fixtures.concert("아이유콘서트");
        given(concertRepository.getConcertInfo(any(),any())).willReturn(concert);
        //when
        Concert result = concertReader.getConcertInfo("20240414","아이유콘서트");
        //then
        assertThat(result.price()).isEqualTo(50000);
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.capacity()).isEqualTo(50);
    }

    @Test
    @DisplayName("예약가능한 콘서트 날짜별로 전부 조회")
    void findAvailableDate() {
        //given
        List<Concert> list = new ArrayList<>();
        Concert concert1 = Fixtures.concert("아이유콘서트");
        Concert concert2 = Fixtures.concert("성시경콘서트");
        list.add(concert1);
        list.add(concert2);

        given(concertRepository.findAvailableConcertAndDate()).willReturn(list);
        //when
        List<Concert> result = concertReader.findAvailableConcertAndDate();
        //then
        assert result.get(0).capacity()>result.get(0).reservedCount();
        assert result.get(1).capacity()>result.get(1).reservedCount();
    }

}
