package com.books.sample.main.injection;

import com.books.sample.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Ivan Martos <ivan.martos@cleverlance.com>
 */
@Subcomponent
public interface IMainActivitySubcomponent extends AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}

}
