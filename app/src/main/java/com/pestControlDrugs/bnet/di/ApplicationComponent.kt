package com.pestControlDrugs.bnet.di

import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import com.pestControlDrugs.bnet.constant.DRUGS_LIST_FRAGMENT
import com.pestControlDrugs.bnet.constant.MAIN
import com.pestControlDrugs.bnet.view.activities.MainActivity
import com.pestControlDrugs.bnet.view.fragment.CurrentDrugsFragment
import com.pestControlDrugs.bnet.view.fragment.DrugsListFragment
import com.pestControlDrugs.bnet.view.fragment.SplashFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component (modules = [AppModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun setContext (context: Context) : Builder
        fun build (): ApplicationComponent
    }
    fun inject(drugsListFragment: DrugsListFragment)
    fun inject(currentDrugsFragment: CurrentDrugsFragment)
}
@Module
class AppModule () {
    @Singleton
    @Provides
    fun provideSharedPreferences (context: Context) : SharedPreferences {
        return context.getSharedPreferences(MAIN, Context.MODE_PRIVATE)
    }
    @Singleton
    @Provides
    fun provideSharedPreferencesEditor (sharedPreferences: SharedPreferences) : SharedPreferences.Editor {
        return sharedPreferences.edit()
    }
}