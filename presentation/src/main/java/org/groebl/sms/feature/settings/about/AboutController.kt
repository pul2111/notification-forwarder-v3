package org.groebl.sms.feature.settings.about

import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import org.groebl.sms.BuildConfig
import org.groebl.sms.R
import org.groebl.sms.common.base.QkController
import org.groebl.sms.common.widget.PreferenceView
import org.groebl.sms.injection.appComponent
import io.reactivex.Observable
import kotlinx.android.synthetic.main.about_controller.*
import javax.inject.Inject

class AboutController : QkController<AboutView, Unit, AboutPresenter>(), AboutView {

    @Inject override lateinit var presenter: AboutPresenter

    init {
        appComponent.inject(this)
        layoutRes = R.layout.about_controller
    }

    override fun onViewCreated() {
        version.summary = BuildConfig.VERSION_NAME
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.bindIntents(this)
        setTitle(R.string.about_title)
        showBackButton(true)
    }

    override fun preferenceClicks(): Observable<PreferenceView> = (0 until preferences.childCount)
            .map { index -> preferences.getChildAt(index) }
            .mapNotNull { view -> view as? PreferenceView }
            .map { preference -> preference.clicks().map { preference } }
            .let { preferences -> Observable.merge(preferences) }

    override fun render(state: Unit) {
        // No special rendering required
    }

}