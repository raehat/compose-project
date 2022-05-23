package com.example.internshipt

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.internshipt.ui.Bazaar
import com.example.internshipt.ui.Charcha
import com.example.internshipt.ui.Profile

typealias ComposableFun = @Composable ()->Unit

class TabItem(val title: String, val screens: ComposableFun) {

}