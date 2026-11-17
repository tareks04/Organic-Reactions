package com.example.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.model.AppLanguage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "organic_chem_prefs")

class PreferencesManager(private val context: Context) {

  private val langKey = stringPreferencesKey("app_language")
  private val bookmarksKey = stringSetPreferencesKey("bookmarked_reaction_ids")

  val languageFlow: Flow<AppLanguage> =
    context.dataStore.data.map { prefs ->
      when (prefs[langKey]) {
        AppLanguage.BN.name -> AppLanguage.BN
        else -> AppLanguage.EN
      }
    }

  val bookmarksFlow: Flow<Set<Int>> =
    context.dataStore.data.map { prefs ->
      prefs[bookmarksKey]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    }

  suspend fun setLanguage(language: AppLanguage) {
    context.dataStore.edit { prefs -> prefs[langKey] = language.name }
  }

  suspend fun toggleBookmark(reactionId: Int) {
    context.dataStore.edit { prefs ->
      val current = prefs[bookmarksKey] ?: emptySet()
      val idStr = reactionId.toString()
      prefs[bookmarksKey] = if (idStr in current) current - idStr else current + idStr
    }
  }
}
