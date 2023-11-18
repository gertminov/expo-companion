package com.example.expo_companion.ui.content.mainUi.sharedComponents

import androidx.compose.runtime.Composable
import com.example.expo_companion.data.Category
import com.example.expo_companion.data.TabItem
import com.example.expo_companion.data.questions.SessionQuestion
import com.example.expo_companion.ui.content.endScreens.routes.RouteEndScreen1
import com.example.expo_companion.ui.viewModels.MainUIViewModel


data class Tab(
    val route: String,
    val icon: Int,
    val content: @Composable () -> Unit
) {
    @Composable
    fun Body() {
        content()
    }

    companion object {
        fun getTabs(
            viewModel: MainUIViewModel,
            usedQuestions: Map<Category, SessionQuestion>
        ): List<Tab> {
            return listOf(
                *categoriesToTabs(viewModel, usedQuestions),
                Tab(
                    TabItem.Companion.Finish.dbName,
                    TabItem.Companion.Finish.icon
                ) { viewModel.onNextClicked(RouteEndScreen1.route) }
            )
        }

        private fun categoriesToTabs(
            vm: MainUIViewModel,
            questions: Map<Category, SessionQuestion>
        ): Array<Tab> {
            return Category.values().map { category ->
                Tab(category.dbName, category.icon) {
                    if (category == Category.NOTES) { //TODO find better solution than this if statement
                        NotesTabBody(isPenSelected = vm.isPenSelected)
                    } else {
                        ScreenDecider(vm, questions[category], category)
                    }
                }
            }.toTypedArray()
        }
    }
}



