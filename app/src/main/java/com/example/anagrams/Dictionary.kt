package com.example.anagrams

import android.util.Log
import java.io.BufferedReader
import java.io.Reader

class Dictionary(reader: Reader) {
    val dict = BufferedReader(reader).readLines()

    /**
     * boolean -> word is on text file
     */
    fun isCorrect(word: String): Boolean =
        dict.contains(word)

    /**
     * returns all the anagrams
     */
    fun anagrams(word: String): List<String> {
        val sortedWord = word.toLowerCase().toCharArray().sorted().joinToString("")//making to char list and then sorting
        return dict.filter {
            val sortedDictWord = it.toLowerCase().toCharArray().sorted().joinToString("") // to small letters and sorting
            it.length == word.length && sortedDictWord == sortedWord && !it.equals(word, ignoreCase = true) // comparing length and sorting
        }
    }
        //function input and dict(is length same?; is sorted string same?;they cant be same words)->filters these dict words as a list


    /**
     * returning all anagrams with anagrams function -> it is not working
     */
    fun allAnagrams(): List<String> {
        return dict.filter { word ->
            val sortedWord = word.toLowerCase().toCharArray().sorted().joinToString("") // sorting word
            dict.any {
                it != word && // not same word
                        it.length == word.length && // controlling is the length same
                        it.toLowerCase().toCharArray().sorted().joinToString("") == sortedWord // and sorted letters
            }
        }
    }

    /**
     returning all the words with 1 letter difference
     1. 1 letter less
     2. replacing
     3. 1 letter more
     */
    fun mistake1(word: String): List<String> {
        val result = mutableListOf<String>()//list where all the 1 word different words
        val alphabet = ('a'..'z')

        // iterating all the words and replacing thwm with letters
        for (i in word.indices) {//for every letter in word
            val newWordRemove = word.removeRange(i, i + 1)// removing letter in position i
            if (isCorrect(newWordRemove)) result.add(newWordRemove)// already same ->to result list

            // trying to replace missing letters with a..z
            for (c in alphabet) {
                val newWordReplace = word.substring(0, i) + c + word.substring(i + 1)
                //starting + c for missing letter + ending
                if (isCorrect(newWordReplace) && newWordReplace != word) result.add(newWordReplace)
            }
        }

        // adding letter to every position
        for (i in 0..word.length) {
            for (c in alphabet) {
                val newWordAdd = word.substring(0, i) + c + word.substring(i)
                if (isCorrect(newWordAdd)) result.add(newWordAdd)
            }
        }

        return result
    }
}
