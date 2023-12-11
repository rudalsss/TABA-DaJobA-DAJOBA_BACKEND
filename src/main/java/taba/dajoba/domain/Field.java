package taba.dajoba.domain;

public enum Field {
    //==직군==//
    DEVELOPMENT(518)      //개발
    , BUSINESS(507)       //경영·비즈니스
    , MARKETING(523)      //마케팅·광고
    , DESIGN(511)         //디자인
    , SALES(530)          //영업
    , SERVICE(510)        //고객서비스·리테일
    , MEDIA(524)          //미디어
    , ENGINEER(513)       //엔지니어링·설계
    , HR(517)             //HR
    , GAME(959)           //게임 제작
    , FINANCE(508)        //금융
    , MANUFACRUE(522)     //제조·생산
    , MEDICAL(515)        //의료·제약·바이오
    , EDU(10101)          //교육
    , LOGISTICS(532)      //물류·무역
    , FOOD(10057)         //식·음료
    , LEGAL(521)          //법률·법집행기관
    , CONSTRUCTION(509)   //건설·시설
    , PUBLIC(514);        //공공·복지

    private final int field;

    Field(int field) {
        this.field = field;
    }

}
