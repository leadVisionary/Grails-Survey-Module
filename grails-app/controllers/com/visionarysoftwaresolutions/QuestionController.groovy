package com.visionarysoftwaresolutions

class QuestionController {
    def questionService
    
    def scaffold = Question
    
    def index = { 
    
    }
    
    def createMultipleChoiceQuestion = {
        try{
            def question = questionService.createMultipleChoiceQuestion(
                params.questionText, 
                [params.answer1, params.answer2, params.answer3])
            redirect action:'show', id: question.id
        }
        catch(AnswerException failed){
            flash.message = failed.message
            flash.answerInstance = failed.problem
            redirect action:'index'
        }
        catch(QuestionException failed){
            flash.message = failed.message
            flash.questionInstance = failed.problem
            redirect action:'index'
        }
    }
}
