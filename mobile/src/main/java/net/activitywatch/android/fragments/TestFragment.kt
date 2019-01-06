package net.activitywatch.android.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import net.activitywatch.android.R
import net.activitywatch.android.RustInterface
import net.activitywatch.android.UsageStatsWatcher
import net.activitywatch.android.models.TestViewModel


class TestFragment : Fragment() {

    companion object {
        fun newInstance() = TestFragment()
    }

    private lateinit var viewModel: TestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TestViewModel::class.java)
        // TODO: Use the ViewModel

        val button = view?.findViewById(R.id.button) as Button
        button.setOnClickListener {
            val ctx = activity
            if(ctx != null) UsageStatsWatcher(ctx).queryUsage()
            testRust()
        }
    }

    private fun testRust() {
        val ctx = activity
        if(ctx != null) {
            RustInterface(ctx).test()
        }
    }
}
