package com.visionarysoftwaresolutions

class QuestionService {

    static transactional = true
    
    def createMultipleChoiceQuestion( String questionText,  
                                      List choices
                                    ) 
    {
        Question newQuestion = new Question( text : questionText )
        choices.each {
            Answer result = create(newQuestion, it.text, it.correct.equals("on") ? true : false)
            newQuestion.addToAnswers(result)
        }
        newQuestion = persistQuestion(newQuestion)
        return newQuestion
    }
    
    private Answer create(Question addTo, String answerText, boolean isCorrect){
        Answer option = new Answer( text: answerText, isCorrect : isCorrect, question:addTo)
        if(!option.validate()) {
            def message = "Could not make an answer for ${option}"
            throw new AnswerException(
                message: message,
                problem: option
            )
        }
        return option
    }
    
    private def persistQuestion(Question newQuestion){
        if(newQuestion.validate()){
            newQuestion.save(flush:true)
        }
        else{
            def message = "Could not make a question for ${newQuestion}"
            throw new QuestionException(
                message : message,
                problem : newQuestion
            )
        }
        return newQuestion
    }
}
