package com.adrian.payment.common.injection

import com.adrian.payment.main.view.DetailFragment
import com.adrian.payment.main.view.ListFragment
import com.adrian.payment.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InjectorBuilder {

    @ContributesAndroidInjector
    @PerActivity
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    @PerFragment
    abstract fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    @PerFragment
    abstract fun bindDetailFragment(): DetailFragment
}