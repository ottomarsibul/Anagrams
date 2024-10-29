Anagram Finder & 1 letter different words finder
This Kotlin code checks if a word is in a dictionary file, is finding anagrams and is finding similar words. The allAnagrams function has issues.

Class Summary
Dictionary: Reads words from a file and stores them in a list (dict).

Main Methods 
1. isCorrect(word: String): Boolean
Checks if a word is in the dictionary file or not.
2. anagrams(word: String): List<String>
Finds all anagrams of a word that match these conditions:
  Same length.
  Same letters when sorted.
  Not an exact match (ignores case).
3. allAnagrams(): List<String>
Attempts to find all dictionary words with anagrams. Currently not working correctly.
4. mistake1(word: String): List<String>
Finds words that differ by one letter by:
  Removing one letter.
  Replacing one letter with each letter from 'a' to 'z'.
  Adding a new letter at each position.

Notes
allAnagrams Issue: The allAnagrams function currently doesn't work as intended.
