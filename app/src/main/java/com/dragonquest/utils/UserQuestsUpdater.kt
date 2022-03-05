package com.dragonquest.utils

import com.dragonquest.models.UserQuest
import com.dragonquest.viewmodels.CharactersViewModel
import com.dragonquest.viewmodels.QuestsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.ISODateTimeFormat

object UserQuestsUpdater {

    val REPEAT_ON = true
    var userQuests = listOf<UserQuest>()
    val parser: DateTimeFormatter = ISODateTimeFormat.dateTime()

    fun startCheckingQuests(chVM: CharactersViewModel, questVM : QuestsViewModel) {
        GlobalScope.launch(Dispatchers.Main) {

            while(REPEAT_ON) {
                checkQuests(chVM, questVM)
                delay(5000L)
            }
        }
    }

    fun checkQuests(chVM: CharactersViewModel, questVM : QuestsViewModel) {
        for (userQuest in userQuests) {
            val (_, status, startDate, quest) = userQuest
            val time = quest.time

            if(status == "PENDING" && startDate !== null && startDate.isNotEmpty()) {
                val parsedTime = parser.parseDateTime(startDate).plusHours(time).toLocalDateTime()
                val now = LocalDateTime.now()

                if(now.isAfter(parsedTime)) {
                    // quest must be first due to finish and change status
                    // TODO implement better solution
                    questVM.initializeData()
                    chVM.initializeData()
                }
            }
        }
    }

    fun addQuestsToObserve(usersQuestsArg : List<UserQuest>) {
        userQuests = usersQuestsArg
    }
}