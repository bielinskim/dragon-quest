package com.dragonquest.characters

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dragonquest.data.characterImages
import com.dragonquest.models.Character

class CharactersViewModel : ViewModel() {

    var characters: MutableLiveData<List<Character>> = MutableLiveData()
    val  charactersImages = characterImages();

    fun initializeData() {
//        viewModelScope.launch(Dispatchers.IO) {
//
//        }

       characters.postValue(listOf(
           Character(1, "Abu", charactersImages["Rose"]),
           Character(2, "Praveen", charactersImages["Lily"]),
           Character(3, "Sathya", charactersImages["Tulip"])
       ))
    }
}