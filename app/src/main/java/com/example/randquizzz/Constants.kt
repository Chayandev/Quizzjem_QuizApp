package com.example.randquizzz

object Constants {
    const val USER_NAME:String="user_name"
    const val TOTAL_QUESTIONS:String="total_questions"
    const val CORRECT_ANSWER:String="correct_answer"
    fun getQuestions():ArrayList<Question>{
        val questionsList=ArrayList<Question>()

        val que1=Question(
            0,"What country dose this flag belong to?",
            R.drawable.newzealand,
            "Argentina","Australia","India","New zealand",
             4
        )
        questionsList.add(que1)
        val que2=Question(1,"What country dose this flag belong to?",
            R.drawable.finland,
            "Argentina","Finland","Pakistan","New zealand",
            2
        )
        questionsList.add(que2)
        val que3=Question(
            2,"What country dose this flag belong to?",
            R.drawable.india,
            "Afghanistan","Australia","India","China",
            3
        )
        questionsList.add(que3)
        val que4=Question(
            3,"What country dose this flag belong to?",
            R.drawable.china,
            "America","Ukraine","India","China",
            4
        )
        questionsList.add(que4)
        val que5=Question(4,"What country dose this flag belong to?",
            R.drawable.serbia,
            "Ukraine","China","serbia","South Korea",
            3
        )
        questionsList.add(que5)
        val que6=Question(
            5,"What country dose this flag belong to?",
            R.drawable.iran,
            "Iran","India","China","Argentina",
            1
        )
        questionsList.add(que6)
        val que7=Question(
            6,"What country dose this flag belong to?",
            R.drawable.israel,
            "Austria","Israel","Pakistan","South Korea",
            2
        )
        questionsList.add(que7)
        val que8=Question(
            7,"What country dose this flag belong to?",
            R.drawable.ukraine,
            "Ukraine","Israel","America","Poland",
            1
        )
        questionsList.add(que8)
        val que9=Question(
            8,"What country dose this flag belong to?",
            R.drawable.russia,
            "New Zealand","West Indies","China","Russia",
            4
        )
        questionsList.add(que9)
        val que10=Question(
            9,"What country dose this flag belong to?",
            R.drawable.poland,
            "China","India","Pakistan","Poland",
            4
        )
        questionsList.add(que10)

        return questionsList
    }
}