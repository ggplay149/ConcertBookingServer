package com.week4.concert;

import com.week4.concert.domain.concert.Concert;

public class Fixtures {
    public static Concert concert(String name){
        if(name.equals("아이유콘서트")){
            return new Concert(1L,"아이유콘서트",50,0,50000,"20240414");
        }
        if(name.equals("실리카겔콘서트")){
            return new Concert(2L,"실리카겔콘서트",50,50,65000,"20240516");
        }
        if(name.equals("성시경콘서트")){
            return new Concert(3L,"성시경콘서트",50,29,70000,"20240807");
        }
        if(name.equals("오아시스콘서트")){
            return new Concert(4L,"오아시스콘서트",50,50,60000,"20241214");
        }
        return null;
    }
}
