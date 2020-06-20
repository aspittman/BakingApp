package com.affinityapps.bakingapp.master;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecipeDetailViewModel extends ViewModel {

    private MutableLiveData<Integer> mPosition = new MutableLiveData<>();
    private MutableLiveData<String> mDescription = new MutableLiveData<>();
    private MutableLiveData<String> mVideoUrl = new MutableLiveData<>();

    public void setPosition(int position) {
        mPosition.setValue(position);
    }

    public void setDescription(String description) {
        mDescription.setValue(description);
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl.setValue(videoUrl);
    }


    public LiveData<Integer> getPosition() {
        return mPosition;
    }

    public LiveData<String> getDescription() {
        return mDescription;
    }

    public LiveData<String> getVideoUrl() {
        return mVideoUrl;
    }
}
