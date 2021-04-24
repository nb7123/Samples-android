package com.mike.samples.initializer

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.startup.Initializer

class AppStartupInitializer: Initializer<Application> {
    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            Log.d(objectTag(f), "onFragmentPreAttached(context: $context)")
        }

        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            Log.d(objectTag(f), "onFragmentAttached(context: $context)")
        }

        override fun onFragmentPreCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            Log.d(objectTag(f), "onFragmentPreCreated(savedState: $savedInstanceState)")
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            Log.d(objectTag(f), "onFragmentCreated(savedState: $savedInstanceState)")
        }

        override fun onFragmentActivityCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            Log.d(objectTag(f), "onFragmentActivityCreated(savedState: $savedInstanceState)")
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            Log.d(objectTag(f), "onFragmentViewCreated(savedState: $savedInstanceState)")
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentStarted()")
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentResumed()")
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentPaused()")
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentStopped()")
        }

        override fun onFragmentSaveInstanceState(
            fm: FragmentManager,
            f: Fragment,
            outState: Bundle
        ) {
            Log.d(objectTag(f), "onFragmentSaveInstanceState(outState: $outState)")
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentViewDestroyed()")
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentDestroyed()")
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            Log.d(objectTag(f), "onFragmentDetached()")
        }
    }

    private val activityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedState: Bundle?) {
            Log.d(objectTag(activity), "onActivityCreated(savedState: $savedState)")

            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, true)
            }
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(objectTag(activity), "onActivityStarted()")
        }

        override fun onActivityResumed(activity: Activity) {
            Log.d(objectTag(activity), "onActivityResumed()")
        }

        override fun onActivityPaused(activity: Activity) {
            Log.d(objectTag(activity), "onActivityPaused()")
        }

        override fun onActivityStopped(activity: Activity) {
            Log.d(objectTag(activity), "onActivityStopped()")
        }

        override fun onActivitySaveInstanceState(activity: Activity, savedState: Bundle) {
            Log.d(objectTag(activity), "onActivitySaveInstanceState(savedState: $savedState)")
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(objectTag(activity), "onActivityDestroyed()")

            if (activity is FragmentActivity) {
                activity.supportFragmentManager
                    .unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
            }
        }
    }

    override fun create(context: Context): Application {
        val application = context.applicationContext as Application
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

        return application
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

    private fun objectTag(obj: Any): String {
        return obj.javaClass.simpleName + ":" + obj.hashCode()
    }
}