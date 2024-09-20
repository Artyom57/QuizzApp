package acosta.alonso.quizzapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle

private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

private const val TAG = "QuizViewModel"
class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel(){
    /*
    init {
        Log.d(TAG, "ViewModel instance created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
    */
    private val questionBank = listOf(
        Question(R.string.pregunta_cielo, false),
        Question(R.string.pregunta_subiarri, false),
        Question(R.string.pregunta_murcielago, true),
        Question(R.string.pregunta_TF, true),
        Question(R.string.pregunta_DB, false)
    )
    private var currentIndex:Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer:Boolean
        get()=questionBank[currentIndex].answer

    val currentQuestionText:Int
        get()=questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrev() {
        currentIndex = if (currentIndex == 0){
            questionBank.size - 1
        }else
            (currentIndex - 1) % questionBank.size
    }


}