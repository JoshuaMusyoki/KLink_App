package com.example.songabiz.presentation.navigation

import com.example.songabiz.R

sealed class NavigationItems (var route:String, var icon: Int, var title: String){
    object Home: NavigationItems("home_page_screen", R.drawable.homeiconred, "Home")
    object  History: NavigationItems("ride_history_screen", R.drawable.timeiconred, "Ride History")
    object  Invite: NavigationItems("invite_friends_screen", R.drawable.coffeeiconred, "Invite Friends")
    object  Support: NavigationItems("online_support_screen", R.drawable.supporticonred, "Online Support")
    object  Settings: NavigationItems("settings_screen", R.drawable.settingsiconred, "Settings")
    object  Wallet: NavigationItems("my_wallet_screen", R.drawable.walleticonred, "My Wallet")
}