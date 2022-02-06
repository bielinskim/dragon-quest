package com.dragonquest.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.data.characterImages
import com.dragonquest.models.Character
import com.dragonquest.models.Quest

class CharactersViewModel : ViewModel() {

    var characters: MutableLiveData<List<Character>> = MutableLiveData()
    val charactersImages = characterImages();

    fun initializeData() {
//        viewModelScope.launch(Dispatchers.IO) {
//
//        }

       characters.postValue(listOf(
           Character(1, "Abu", 0, charactersImages[1]),
           Character(2, "Praveen", 1200, charactersImages[2]),
           Character(3, "Sathya", 13999, charactersImages[3]),
           Character(4, "Ailen", 38000, charactersImages[4])
       ))
    }
}