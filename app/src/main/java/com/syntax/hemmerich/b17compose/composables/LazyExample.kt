package com.syntax.hemmerich.b17compose.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.syntax.hemmerich.b17compose.Note
import com.syntax.hemmerich.b17compose.NoteItem


@Composable
fun LazyExample(notes: List<Note>,navController: NavController){
    LazyColumn {
        items(notes){  note ->
            NoteItem(note = note,navController)
            //Text(text = note.date)
        }
    }
}


