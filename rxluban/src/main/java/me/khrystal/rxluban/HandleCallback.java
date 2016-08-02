package me.khrystal.rxluban;

/**
 * usage:
 * author: kHRYSTAL
 * create time: 16/8/2
 * update time:
 * email: 723526676@qq.com
 */
public interface HandleCallback<T> {

    void beforeHandle();

    void handleError(String msg);

    void handleComplete();

    void handleSuccess(T data);
}
