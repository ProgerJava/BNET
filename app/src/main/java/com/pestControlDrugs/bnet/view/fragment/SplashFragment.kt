package com.pestControlDrugs.bnet.view.fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pestControlDrugs.bnet.constant.DRUGS_LIST_FRAGMENT
import com.pestControlDrugs.bnet.databinding.FragmentSplashBinding
import com.pestControlDrugs.bnet.di.MyApplication
import com.pestControlDrugs.bnet.view.activities.MainActivity
import javax.inject.Inject


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        startCountDownTimer()

        return binding.root
    }
    private fun startCountDownTimer () {
        object: CountDownTimer (2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                (requireActivity() as MainActivity).changeFragment(DRUGS_LIST_FRAGMENT)
            }
        }.start()
    }

}