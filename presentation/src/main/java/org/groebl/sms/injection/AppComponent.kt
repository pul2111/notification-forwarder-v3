/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
 *
 * This file is part of QKSMS.
 *
 * QKSMS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * QKSMS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with QKSMS.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.groebl.sms.injection

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import org.groebl.sms.common.QKApplication
import org.groebl.sms.common.QkDialog
import org.groebl.sms.common.util.QkChooserTargetService
import org.groebl.sms.common.widget.*
import org.groebl.sms.feature.backup.BackupController
import org.groebl.sms.feature.bluetooth.BluetoothSettingsController
import org.groebl.sms.feature.compose.DetailedChipView
import org.groebl.sms.feature.conversationinfo.injection.ConversationInfoComponent
import org.groebl.sms.feature.settings.SettingsController
import org.groebl.sms.feature.settings.about.AboutController
import org.groebl.sms.feature.settings.swipe.SwipeActionsController
import org.groebl.sms.feature.themepicker.injection.ThemePickerComponent
import org.groebl.sms.feature.widget.WidgetAdapter
import org.groebl.sms.injection.android.ActivityBuilderModule
import org.groebl.sms.injection.android.BroadcastReceiverBuilderModule
import org.groebl.sms.injection.android.ServiceBuilderModule
import org.groebl.sms.util.ContactImageLoader
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ServiceBuilderModule::class])
interface AppComponent {

    fun conversationInfoBuilder(): ConversationInfoComponent.Builder
    fun themePickerBuilder(): ThemePickerComponent.Builder

    fun inject(application: QKApplication)

    fun inject(controller: AboutController)
    fun inject(controller: BackupController)
    fun inject(controller: SettingsController)
    fun inject(controller: BluetoothSettingsController)
    fun inject(controller: SwipeActionsController)

    fun inject(dialog: QkDialog)

    fun inject(fetcher: ContactImageLoader.ContactImageFetcher)

    fun inject(service: WidgetAdapter)

    /**
     * This can't use AndroidInjection, or else it will crash on pre-marshmallow devices
     */
    fun inject(service: QkChooserTargetService)

    fun inject(view: AvatarView)
    fun inject(view: DetailedChipView)
    fun inject(view: PagerTitleView)
    fun inject(view: PreferenceView)
    fun inject(view: QkEditText)
    fun inject(view: QkSwitch)
    fun inject(view: QkTextView)

}