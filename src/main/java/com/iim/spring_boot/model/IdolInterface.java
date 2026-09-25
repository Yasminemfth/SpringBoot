package com.iim.spring_boot.model;

public interface IdolInterface {
    String HotTake(int badBuzz);
    int getPopularite( int BadBuzz, int Convention);
    void baisserPopularite(int nombre, int Popularite, int BadBuzz);
    void augmenterPopularite(int nombre, int Popularite, int Convention);
}
