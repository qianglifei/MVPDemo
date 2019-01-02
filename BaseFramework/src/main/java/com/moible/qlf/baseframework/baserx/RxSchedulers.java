package com.moible.qlf.baseframework.baserx;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxSchedulers {

    public static <T> FlowableTransformer<T, T> io_main() {

        return new FlowableTransformer<T, T>() {

            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return  upstream.
                        //被观察者切换到子线程
                        subscribeOn(Schedulers.io()).
                        //观察者切换到主线程
                        observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
