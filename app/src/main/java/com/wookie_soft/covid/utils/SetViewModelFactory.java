package com.wookie_soft.covid.utils;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.wookie_soft.covid.presentation.MyVeiwModel;

public class SetViewModelFactory implements ViewModelProvider.Factory {

    private Application application;

    public SetViewModelFactory(Application application)
    {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> aClass)
    {
        return (T) new MyVeiwModel(application);
    }
}
