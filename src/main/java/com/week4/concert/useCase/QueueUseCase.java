package com.week4.concert.useCase;

import com.week4.concert.domain.queue.ongoing.OngoingSerivce;
import com.week4.concert.domain.queue.waiting.Waiting;
import com.week4.concert.domain.queue.waiting.WaitingService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueueUseCase {

    private final WaitingService waitingService;
    private final OngoingSerivce ongoingSerivce;

    public QueueUseCase(WaitingService waitingService, OngoingSerivce ongoingSerivce) {
        this.waitingService = waitingService;
        this.ongoingSerivce = ongoingSerivce;
    }

    public void queueUpdate(){

        int ongoingCount = ongoingSerivce.countOngoing();

        if(ongoingCount < 50){

            List<Waiting> nextUserList =  waitingService.selectTopN(50-ongoingCount);

            for(Waiting waitingUser : nextUserList){

                ongoingSerivce.insert(waitingUser.userId());

                waitingService.remove(waitingUser.id());

            }
        }
    }
}
