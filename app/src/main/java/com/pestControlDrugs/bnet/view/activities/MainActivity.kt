package com.pestControlDrugs.bnet.view.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.pestControlDrugs.bnet.R
import com.pestControlDrugs.bnet.constant.CURRENT_DRUGS_FRAGMENT
import com.pestControlDrugs.bnet.constant.DRUGS_LIST_FRAGMENT
import com.pestControlDrugs.bnet.databinding.ActivityMainBinding
import com.pestControlDrugs.bnet.constant.SPLASH_FRAGMENT
import com.pestControlDrugs.bnet.di.MyApplication
import com.pestControlDrugs.bnet.tools.showToast
import com.pestControlDrugs.bnet.view.fragment.CurrentDrugsFragment
import com.pestControlDrugs.bnet.view.fragment.DrugsListFragment
import com.pestControlDrugs.bnet.view.fragment.SplashFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    ///////////////Управлчение фрагментами
    private lateinit var fragmentTransaction: FragmentTransaction
    private var backPressedTime = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ////////////////Подключаем первый экран
        changeFragment(SPLASH_FRAGMENT)


    }
    fun changeFragment (fragmentName: String) {
        fragmentTransaction = supportFragmentManager.beginTransaction()

        when (fragmentName) {
            SPLASH_FRAGMENT -> {fragmentTransaction.add(R.id.frameLayout, SplashFragment()).commit()}
            DRUGS_LIST_FRAGMENT -> {fragmentTransaction.replace(R.id.frameLayout, DrugsListFragment()).commit()}
            CURRENT_DRUGS_FRAGMENT -> {fragmentTransaction.replace(R.id.frameLayout, CurrentDrugsFragment()).commit()}
        }
    }
    ////////////Кнопка выхода из приложения
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            showToast(this, getString(R.string.onBackPressedRegistration))
            backPressedTime = System.currentTimeMillis()
        }
    }
}