package com.example.check.ui.main;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;




public class PageViewModel extends ViewModel {

    private String id;
    private int index;

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return id;
        }
    });

    public void setIndex(int index) {
        this.index = index;
        mIndex.setValue(index);
    }
    public int getIndex() {
        return this.index;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setId(String id){
        this.id = id;
    }

}