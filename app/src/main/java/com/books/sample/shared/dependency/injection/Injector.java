package com.books.sample.shared.dependency.injection;

public enum Injector {
    INSTANCE;

    private IApplicationComponent applicationComponent;

    public void initialize() {
        applicationComponent = DaggerIApplicationComponent.create();
    }

    public IApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
