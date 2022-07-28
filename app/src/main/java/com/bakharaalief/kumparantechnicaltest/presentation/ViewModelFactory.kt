package com.bakharaalief.kumparantechnicaltest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.kumparantechnicaltest.di.Injection
import com.bakharaalief.kumparantechnicaltest.domain.usecase.PostUseCase
import com.bakharaalief.kumparantechnicaltest.presentation.main.MainViewModel
import com.bakharaalief.kumparantechnicaltest.presentation.postDetail.PostDetailViewModel

class ViewModelFactory(
    private val postUseCase: PostUseCase,
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(postUseCase) as T
            modelClass.isAssignableFrom(PostDetailViewModel::class.java) -> PostDetailViewModel(
                postUseCase
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providePostUseCase())
            }.also { instance = it }
    }
}