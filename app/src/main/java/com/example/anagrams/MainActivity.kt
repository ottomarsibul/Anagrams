package com.example.anagrams

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.anagrams.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStreamReader
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dictionary: Dictionary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_anagrams)
        try {
            val inputStream = getAssets().open("words.txt")
            dictionary = Dictionary(InputStreamReader(inputStream))
            Toast.makeText(this, "Dictionary loaded", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Could not load dictionary", Toast.LENGTH_LONG).show()
        }
        Toast.makeText(this, "start", Toast.LENGTH_LONG).show()
        binding.anagramBtn.isChecked = true
        binding.editText.setOnEditorActionListener { v, actionId, event ->
            //Toast.makeText(this, "actionId = $actionId, event = $event", Toast.LENGTH_LONG).show()
            val word = binding.editText.text.toString().trim().lowercase(Locale.ROOT)
            if (word.length > 0 && dictionary.isCorrect(word)) {
                processWord(
                    word,
                    if (binding.isCorrectBtn.isChecked) listOf(
                        if (dictionary.isCorrect(word)) "correct" else "incorrect"
                    )
                    else if (binding.anagramBtn.isChecked) dictionary.anagrams(word)
                    else if (binding.mistakeBtn.isChecked) dictionary.mistake1(word)
                    else dictionary.allAnagrams()
                )
            } else {
                Toast.makeText(this, "not a correct word", Toast.LENGTH_LONG).show()
            }
            true
        }
    }

    private fun processWord(word: String, lst: List<String>) {
        if (lst.count() == 0) {
            Toast.makeText(this, "there are no correct results for $word", Toast.LENGTH_LONG).show()
            return
        }
        binding.apply {
            resultView.text = ""
            editText.setText("")
            for (ana in lst) {
                val source: String = String.format(
                    "<font color=%s>%s</font><BR>",
                    "#cc0029",
                    ana
                )
                resultView.append(
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                        Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
                    else
                        Html.fromHtml(source)
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_anagrams, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return if (id == R.id.action_settings)
            true
        else super.onOptionsItemSelected(item)
    }
}