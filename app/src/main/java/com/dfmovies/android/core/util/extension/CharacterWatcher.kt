package com.dfmovies.android.core.util.extension

import android.text.Editable
import android.text.TextWatcher

abstract class CharacterWatcher : TextWatcher {
    override fun afterTextChanged(text: Editable?) {
        afterCharacterChanged(text?.lastOrNull(), text?.length)
    }

    override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, before: Int) {}

    override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {}

    abstract fun afterCharacterChanged(char: Char?, count: Int?)
}