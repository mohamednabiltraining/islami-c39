package com.route.islamic39.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val dataStore = context.dataStore

    companion object {
        val darkModeKey = booleanPreferencesKey("DARK_MODE")
    }

    suspend fun saveMode(mode: Boolean) {
        dataStore.edit { preference ->
            preference[darkModeKey] = mode
        }
    }

    fun getMode(): Flow<Boolean> {
        return dataStore
            .data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                val uiMode = preference[darkModeKey] ?: false
                uiMode
            }
    }
}
